package com.logonedigital.gestion_stock_g7.dto.products;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record ProductReqDTO(
        @NotEmpty(message = "Please fill product's name")
        String name,
        @NotEmpty(message = "Please fill product's description")
        String description,
        @Pattern(regexp = "^[0-9]+$", message = "Only number")
        @NotEmpty(message = "Please fill product's price")
        Double price,
        @Pattern(regexp = "^[0-9]+$", message = "Only number")
        @NotEmpty(message = "Please fill product's price")
        Integer categoryId,
        ProductStockReqDTO productStockReqDTO
) {
}
