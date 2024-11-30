package com.logonedigital.gestion_stock_g7.services.products;

import com.github.slugify.Slugify;
import com.logonedigital.gestion_stock_g7.entities.CategoryProducts;
import com.logonedigital.gestion_stock_g7.exception.ResourceNotFoundException;
import com.logonedigital.gestion_stock_g7.repositories.CategoryProductsRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryProductsImpl implements CategoryProductService {

    private final CategoryProductsRepo categoryProductsRepo;

    public CategoryProductsImpl(CategoryProductsRepo categoryProductsRepo) {
        this.categoryProductsRepo = categoryProductsRepo;

    }

    @Override
    public CategoryProducts addCategoryProduct(CategoryProducts categoryProducts) {
        final Slugify slg = Slugify.builder().build();
        categoryProducts.setCreatedAt(new Date());
        categoryProducts.setSlug(slg.slugify(categoryProducts.getName()));
        categoryProducts.setStatus(true);

        return this.categoryProductsRepo.save(categoryProducts);
    }

    @Override
    public List<CategoryProducts> getAllCategoryProducts() {
        return this.categoryProductsRepo.findAll();
    }

    @Override
    public CategoryProducts getCategoryProductById(Integer categoryProductsId) {

        return this.categoryProductsRepo.findById(categoryProductsId).orElseThrow(()-> new ResourceNotFoundException("Category Product not found !"));
    }

    @Override
    public CategoryProducts updateCategoryProductsById(Integer categoryProductsId, CategoryProducts categoryProducts) {
        final Slugify slg = Slugify.builder().build();

        CategoryProducts categoryProductsToEdit = this.categoryProductsRepo.findById(categoryProductsId).orElseThrow(()->new ResourceNotFoundException("Category product not found !"));

        categoryProductsToEdit.setName(categoryProducts.getName());
        categoryProductsToEdit.setDescription(categoryProducts.getDescription());
        categoryProductsToEdit.setSlug(slg.slugify(categoryProducts.getName()));
        categoryProductsToEdit.setUpdatedAt(new Date());


        return this.categoryProductsRepo.saveAndFlush(categoryProductsToEdit);
    }

    @Override
    public void deleteCategoryProduct(Integer categoryProductsId) {

        this.categoryProductsRepo.delete(categoryProductsRepo
                .findById(categoryProductsId)
                .orElseThrow(()->new ResourceNotFoundException("Category product not found !")));
    }
}
