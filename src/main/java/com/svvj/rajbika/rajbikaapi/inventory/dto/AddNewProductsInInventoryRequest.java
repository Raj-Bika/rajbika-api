package com.svvj.rajbika.rajbikaapi.inventory.dto;


import com.svvj.rajbika.rajbikaapi.inventory.model.AddInventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddNewProductsInInventoryRequest {
    private List<AddInventory> addInventories;
}
