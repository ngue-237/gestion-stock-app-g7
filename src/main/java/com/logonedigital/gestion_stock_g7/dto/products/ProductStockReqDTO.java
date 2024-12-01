package com.logonedigital.gestion_stock_g7.dto.products;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record ProductStockReqDTO(
        @Pattern(regexp = "^[0-9]+$", message = "Only number")
        @NotEmpty(message = "Fill quantity")
        Double quantity
)
{
}
