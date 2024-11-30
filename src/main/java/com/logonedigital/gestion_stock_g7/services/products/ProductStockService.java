package com.logonedigital.gestion_stock_g7.services.products;


import com.logonedigital.gestion_stock_g7.entities.ProductsStock;

import java.util.List;

public interface ProductStockService {
    ProductsStock addProductStock(ProductsStock productsStock);
    List<ProductsStock> getProductStock();

}
