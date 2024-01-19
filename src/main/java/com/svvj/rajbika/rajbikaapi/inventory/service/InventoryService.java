package com.svvj.rajbika.rajbikaapi.inventory.service;

import com.svvj.rajbika.rajbikaapi.inventory.dto.AddNewProductsInInventoryRequest;
import com.svvj.rajbika.rajbikaapi.inventory.exceptions.SKUCodeNotFoundException;
import com.svvj.rajbika.rajbikaapi.inventory.model.AddInventory;
import com.svvj.rajbika.rajbikaapi.inventory.model.Inventory;
import com.svvj.rajbika.rajbikaapi.inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isAvailable(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

    public Integer getQuantity(String skuCode) throws SKUCodeNotFoundException {
        List<Inventory> inventoryList =  inventoryRepository.findBySkuCode(skuCode).stream().toList();
        try {
            return inventoryList.get(0).getQuantity();
        }catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new SKUCodeNotFoundException();
        }
    }

    public void updateQuantity(String skuCode, Integer quantity) throws SKUCodeNotFoundException {
        Optional<Inventory> optionalInventory =  inventoryRepository.findBySkuCode(skuCode);
        if(!optionalInventory.isPresent()) {
            throw new SKUCodeNotFoundException();
        }

        Inventory inventory = (Inventory) optionalInventory.stream().toArray()[0];

        log.info("inventory:" + inventory);

        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);

    }

    public void addProductsInInventory(AddNewProductsInInventoryRequest addNewProductsInInventoryRequest) {
        List<Inventory> inventoryList = addNewProductsInInventoryRequest
                .getAddInventories()
                .stream()
                .map(this::mapAddInventoryToInventory)
                .toList();

        inventoryRepository.saveAll(inventoryList);

    }

    private Inventory mapAddInventoryToInventory(AddInventory addInventory) {
        return Inventory
                .builder()
                .id(UUID.randomUUID().toString())
                .quantity(addInventory.getQuantity())
                .skuCode(addInventory.getSkuCode())
                .build();
    }
}
