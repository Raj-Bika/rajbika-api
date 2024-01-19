package com.svvj.rajbika.rajbikaapi.inventory.repository;

import com.svvj.rajbika.rajbikaapi.inventory.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InventoryRepository extends MongoRepository<Inventory, Integer> {
    Optional<Inventory> findBySkuCode(String skuCode);
}
