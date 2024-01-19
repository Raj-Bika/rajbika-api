package com.svvj.rajbika.rajbikaapi.category.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateCategoryRequest {
    @NotEmpty @NotNull
    private String name;
}
