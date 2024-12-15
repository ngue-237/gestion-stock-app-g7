package com.logonedigital.gestion_stock_g7.dto.products.productdto;

import lombok.Data;

@Data
public class ProductToOrderDTO {
    private Integer productID;
    private String name;
    private String slug;
    private String description;
    private Double price;
}
