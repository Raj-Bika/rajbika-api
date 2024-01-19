package com.svvj.rajbika.rajbikaapi.products.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductResponse {
    @Id
    private String id;
    private String productName;
    private String productDescription;
    private String productPrice;
}
