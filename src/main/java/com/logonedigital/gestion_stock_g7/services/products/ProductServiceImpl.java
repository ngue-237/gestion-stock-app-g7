package com.logonedigital.gestion_stock_g7.services.products;

import com.github.slugify.Slugify;
import com.logonedigital.gestion_stock_g7.entities.Product;
import com.logonedigital.gestion_stock_g7.entities.ProductsStock;
import com.logonedigital.gestion_stock_g7.exception.ResourceNotFoundException;
import com.logonedigital.gestion_stock_g7.repositories.ProductRepo;
import com.logonedigital.gestion_stock_g7.repositories.ProductsStockRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepo;
    private final ProductsStockRepo productsStockRepo;

    public ProductServiceImpl(ProductRepo productRepo, ProductsStockRepo productsStockRepo) {
        this.productRepo = productRepo;
        this.productsStockRepo = productsStockRepo;
    }

    @Override
    public Product addProduct(Product product) {
        final Slugify slg = Slugify.builder().build();

        product.setCreatedAt(new Date());
        product.setSlug(slg.slugify(product.getName()));
        product.setStatus(true);

        ProductsStock productsStock1 = product.getProductsStock();
        productsStock1.setStatus(true);
        productsStock1.setCreatedAt(new Date());
        product.setProductsStock(this.productsStockRepo.save(productsStock1));

        return this.productRepo.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return this.productRepo.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        Optional<Product> product = this.productRepo.findById(id);
        if(product.isEmpty())
            throw new ResourceNotFoundException("This product doesn't exist !");
        return product.get();
    }

    @Override
    public List<Product> getActiveProducts() {
        return this.productRepo.fetchActiveProduct();
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
        final Slugify slg = Slugify.builder().build();

        Product productToUpdate = this.productRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));

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
        Product productToEdit = this.productRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found !"));
        if(productToEdit.getStatus())
            productToEdit.setStatus(false);
        else
            productToEdit.setStatus(true);
        productToEdit.setUpdatedAt(new Date());
        return this.productRepo.saveAndFlush(productToEdit);
    }

    @Override
    public void deleteProduct(Integer id) {

        this.productRepo.delete(this.productRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found")));
    }
}
