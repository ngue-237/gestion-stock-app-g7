package com.logonedigital.gestion_stock_g7.services.products;

import com.logonedigital.gestion_stock_g7.entities.CategoryProducts;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryProductService {
    CategoryProducts addCategoryProduct(CategoryProducts categoryProducts);
    List<CategoryProducts> getAllCategoryProducts();
    CategoryProducts getCategoryProductById(Integer categoryProductsId);
    CategoryProducts updateCategoryProductsById(Integer categoryProductsId, CategoryProducts categoryProducts);
    void deleteCategoryProduct(Integer categoryProductsId);
}
