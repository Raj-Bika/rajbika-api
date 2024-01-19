package com.svvj.rajbika.rajbikaapi.inventory.controller;

import com.svvj.rajbika.rajbikaapi.inventory.dto.AddNewProductsInInventoryRequest;
import com.svvj.rajbika.rajbikaapi.inventory.dto.CheckStockQuantityResponse;
import com.svvj.rajbika.rajbikaapi.inventory.exceptions.SKUCodeNotFoundException;
import com.svvj.rajbika.rajbikaapi.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String skuCode) {
        return inventoryService.isAvailable(skuCode);
    }

    @GetMapping("/{sku-code}/quantity")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CheckStockQuantityResponse> getStockQuantity(@PathVariable("sku-code") String skuCode) {
        try {
            return new ResponseEntity<>(CheckStockQuantityResponse
                                                .builder()
                                                .quantity(inventoryService.getQuantity(skuCode))
                                                .build(),
                                        HttpStatus.OK
            );
        } catch (SKUCodeNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> updateQuantity(@PathVariable("sku-code") String skuCode, @RequestParam() Integer quantity) {
        log.info("Updating Quantity for: " + skuCode + " And quantity is: " + quantity);
        try {
            inventoryService.updateQuantity(skuCode, quantity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SKUCodeNotFoundException skuCodeNotFoundException) {
            // TODO: Handle Exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HttpStatus> addNewProductsInInventory(@RequestBody() AddNewProductsInInventoryRequest addNewProductsInInventoryRequest) {
        log.info("Adding new products in inventory: " + addNewProductsInInventoryRequest);
        try {
            inventoryService.addProductsInInventory(addNewProductsInInventoryRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            // TODO: Handle Exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}