package com.ws101.busa.balading.ecommerceapiv2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateProductDto(
        @NotBlank(message = "Product name is required")
        @Size(min = 2, max = 100, message = "Product name must be 2-100 characters")
        String prodName,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        Double prodPrice,

        @Size(max = 500, message = "Description cannot exceed 500 characters")
        String prodDescription
) {}