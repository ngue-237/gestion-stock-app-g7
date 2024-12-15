package com.logonedigital.gestion_stock_g7.dto.products.productdto;

import com.logonedigital.gestion_stock_g7.dto.products.CategoryProductResDTO;
import com.logonedigital.gestion_stock_g7.dto.products.ProductStockResDTO;
import lombok.Data;

@Data
public class ProductDTO {
    private String productId;
    private String name;
    private String slug;
    private String description;
    private Double price;
    private Boolean status;
    private ProductStockResDTO productStockResDTO;
    private CategoryProductResDTO categoryProductResDTO;
}
