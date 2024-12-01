package com.logonedigital.gestion_stock_g7.dto.customer;

import lombok.Data;

@Data
public class LocationReqDTO {
    private String town;
    private Integer postalCode;
    private String street;
}
