package com.svvj.rajbika.rajbikaapi.usercart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CartItems {
    private String productId;
    private Integer quantity;
    private String skuCode;
    private String productName;
    private BigDecimal pricePerUnit;
}
