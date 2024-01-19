package com.svvj.rajbika.rajbikaapi.products.model;

import com.svvj.rajbika.rajbikaapi.category.model.Category;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    private String id;
    private String productName;
    private String productDescription;
    private String productPrice;
    private String skuCode;
    @DBRef
    @NotNull(message = "Product category is mandatory")
    private Category category;
}