package com.logonedigital.gestion_stock_g7.dto.customer;

import jakarta.validation.constraints.Email;

public record CustomerResDTO(
        String firstname,
        String lastname,
        String email,
        String phone,
        Boolean status,
        LocationResDTO locationResDTO
) {
}
