package com.svvj.rajbika.rajbikaapi.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddInventory {
    private String skuCode;
    private Integer quantity;
}
