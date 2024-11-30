package com.logonedigital.gestion_stock_g7.services.products;

import com.logonedigital.gestion_stock_g7.entities.ProductsStock;
import com.logonedigital.gestion_stock_g7.repositories.ProductsStockRepo;

import java.util.Date;
import java.util.List;

public class ProductStockServiceImpl implements ProductStockService{
    private final ProductsStockRepo productsStockRepo;

    public ProductStockServiceImpl(ProductsStockRepo productsStockRepo) {
        this.productsStockRepo = productsStockRepo;
    }


    @Override
    public ProductsStock addProductStock(ProductsStock productsStock) {
        productsStock.setCreatedAt(new Date());
        productsStock.setStatus(true);
        return this.productsStockRepo.save(productsStock);
    }

    @Override
    public List<ProductsStock> getProductStock() {
        return this.productsStockRepo.findAll();
    }
}
