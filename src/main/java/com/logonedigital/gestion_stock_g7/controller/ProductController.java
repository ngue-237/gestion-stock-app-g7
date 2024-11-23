package com.logonedigital.gestion_stock_g7.controller;

import com.logonedigital.gestion_stock_g7.entities.Product;
import com.logonedigital.gestion_stock_g7.services.products.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/products/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){

        return ResponseEntity
                .status(201)
                .body(this.productService.addProduct(product));
    }

    @GetMapping(path = "products/get_all")
    public ResponseEntity<List<Product>> getProducts(){

        return ResponseEntity
                .status(200)
                .body(this.productService.getProducts());
    }

    @GetMapping(path = "products/get_all_activated")
    public ResponseEntity<List<Product>> getProductsActivate(){

        return ResponseEntity
                .status(200)
                .body(this.productService.getActiveProducts());
    }

    @GetMapping(path = "products/get_by_id/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "productId") Integer productId){
        return ResponseEntity
                .status(200)
                .body(this.productService.getProductById(productId));
    }


    @PutMapping(path = "products/update_by_id/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody Product product){

        return ResponseEntity
                .status(202)
                .body(this.productService.updateProduct(productId, product));
    }

    @PutMapping(path = "products/editStatus/{productId}")
    public ResponseEntity<Product> disableProduct(@PathVariable Integer productId){

        return ResponseEntity
                .status(202)
                .body(this.productService.editProductStatus(productId));
    }

    @DeleteMapping(path = "products/delete_by_id/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId){
        this.productService.deleteProduct(productId);
        return ResponseEntity
                .status(202)
                .body("Delete successfully!");
    }
}