package com.logonedigital.gestion_stock_g7.services.products;

import com.logonedigital.gestion_stock_g7.dto.products.productdto.ProductReqDTO;
import com.logonedigital.gestion_stock_g7.dto.products.productdto.ProductResDTO;
import com.logonedigital.gestion_stock_g7.entities.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductReqDTO productReqDTO);
    ProductResDTO getProducts(int pageNumber, int pageSize);
    Product getProductById(Integer id);
    List<Product> getActiveProducts();
    Product updateProduct(Integer id, Product product);
    Product editProductStatus(Integer id);
    void deleteProduct(Integer id);

}
