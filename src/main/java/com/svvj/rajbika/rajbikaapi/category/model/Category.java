package com.svvj.rajbika.rajbikaapi.category.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("categories")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    private String id;
    @NotNull
    @NotEmpty
    @Indexed(unique = true)
    private String name;

}
