package com.svvj.rajbika.rajbikaapi.usercart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(value = "user-cart")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Cart {
    @Id
    private String id;
    private String userId;
    private List<CartItems> cartItemsList;
    private BigDecimal totalCartValue;
    private BigDecimal totalDiscount;
}