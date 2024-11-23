package com.logonedigital.gestion_stock_g7.services.products;

import com.github.slugify.Slugify;
import com.logonedigital.gestion_stock_g7.entities.Product;
import com.logonedigital.gestion_stock_g7.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product addProduct(Product product) {
        final Slugify slg = Slugify.builder().build();

        product.setCreatedAt(new Date());
        product.setSlug(slg.slugify(product.getName()));
        product.setStatus(true);
        return this.productRepo.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return this.productRepo.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return this.productRepo.findById(id).get();
    }

    @Override
    public List<Product> getActiveProducts() {
        return this.productRepo.fetchActiveProduct();
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
        final Slugify slg = Slugify.builder().build();

        Product productToUpdate = this.productRepo.findById(id).get();

        if(product.getName() != null){
            productToUpdate.setName(product.getName());
            productToUpdate.setSlug(slg.slugify(product.getName()));
        }
        if(product.getDescription()!=null)
            productToUpdate.setDescription(product.getDescription());

        if(product.getPrice() !=null)
            productToUpdate.setPrice(product.getPrice());
        productToUpdate.setUpdatedAt(new Date());

        return this.productRepo.saveAndFlush(productToUpdate);
    }

    @Override
    public Product editProductStatus(Integer id) {
        Product productToEdit = this.productRepo.findById(id).get();
        if(productToEdit.getStatus())
            productToEdit.setStatus(false);
        else
            productToEdit.setStatus(true);
        productToEdit.setUpdatedAt(new Date());
        return this.productRepo.saveAndFlush(productToEdit);
    }

    @Override
    public void deleteProduct(Integer id) {
        this.productRepo.deleteById(id);
    }
}
