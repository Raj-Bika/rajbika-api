package com.svvj.rajbika.rajbikaapi.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductRequest {
    private String productName;
    private String productDescription;
    private String productPrice;
    private String skuCode;
    private String categoryId;
}
