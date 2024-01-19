package com.svvj.rajbika.rajbikaapi.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CheckStockQuantityResponse {
    private Integer quantity;
}
