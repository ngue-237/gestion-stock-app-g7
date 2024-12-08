package com.logonedigital.gestion_stock_g7.services.products;

import com.logonedigital.gestion_stock_g7.dto.products.ProductReqDTO;
import com.logonedigital.gestion_stock_g7.entities.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductReqDTO productReqDTO);
    List<Product> getProducts();
    Product getProductById(Integer id);
    List<Product> getActiveProducts();
    Product updateProduct(Integer id, Product product);
    Product editProductStatus(Integer id);
    void deleteProduct(Integer id);

}
