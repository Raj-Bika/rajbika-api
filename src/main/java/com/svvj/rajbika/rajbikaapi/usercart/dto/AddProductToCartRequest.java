package com.svvj.rajbika.rajbikaapi.usercart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddProductToCartRequest {
    private String productId;
    private Integer quantity;
}
