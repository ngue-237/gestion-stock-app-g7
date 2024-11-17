package com.logonedigital.gestion_stock_g7.controller;

import com.logonedigital.gestion_stock_g7.entities.CategoryProducts;
import com.logonedigital.gestion_stock_g7.services.products.CategoryProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryProductController {
    private final CategoryProductService categoryProduitService;

    public CategoryProductController(CategoryProductService categoryProduitService) {
        this.categoryProduitService = categoryProduitService;
    }

    @PostMapping(path = "categories_product/add")
    public ResponseEntity<CategoryProducts> addCategoryProduct(@RequestBody CategoryProducts categoryProducts){

        return ResponseEntity
                .status(201)
                .body(this.categoryProduitService.addCategoryProduct(categoryProducts));
    }

    @GetMapping(path = "categories_product/get_all")
    public ResponseEntity<List<CategoryProducts>> getCategoriesProduct(){
        return ResponseEntity
                .status(200)
                .body(this.categoryProduitService.getAllCategoryProducts());
    }

    @GetMapping(path = "categories_product/get_by_id/{id}")
    public ResponseEntity<CategoryProducts> getCategoryProductById(@PathVariable Integer id){
        return ResponseEntity
                .status(200)
                .body(this.categoryProduitService.getCategoryProductById(id));
    }

    @PatchMapping(path = "categories_product/update_by_id/{id}")
    public ResponseEntity<CategoryProducts> updateCategoryProduct(@PathVariable Integer id, @RequestBody CategoryProducts categoryProducts){
        return ResponseEntity
                .status(202)
                .body(this.categoryProduitService.updateCategoryProductsById(id,categoryProducts));
    }

    @DeleteMapping(path = "categories_product/delete_by_id/{id}")
    public ResponseEntity<String> deleteCategoryProduct(@PathVariable Integer id){

        this.categoryProduitService.deleteCategoryProduct(id);

        return ResponseEntity
                .status(202)
                .body("Deleted successfully !");
    }



}
