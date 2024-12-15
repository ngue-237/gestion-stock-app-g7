package com.logonedigital.gestion_stock_g7.services.products;

import com.github.slugify.Slugify;
import com.logonedigital.gestion_stock_g7.dto.products.CategoryProductResDTO;
import com.logonedigital.gestion_stock_g7.dto.products.productdto.ProductDTO;
import com.logonedigital.gestion_stock_g7.dto.products.productdto.ProductReqDTO;
import com.logonedigital.gestion_stock_g7.dto.products.productdto.ProductResDTO;
import com.logonedigital.gestion_stock_g7.dto.products.ProductStockResDTO;
import com.logonedigital.gestion_stock_g7.entities.Product;
import com.logonedigital.gestion_stock_g7.entities.ProductsStock;
import com.logonedigital.gestion_stock_g7.exception.ResourceNotFoundException;
import com.logonedigital.gestion_stock_g7.repositories.ProductRepo;
import com.logonedigital.gestion_stock_g7.repositories.ProductsStockRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private ProductDTO mapToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductId(productDTO.getProductId());
        productDTO.setName(product.getName());
        productDTO.setSlug(product.getSlug());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setStatus(product.getStatus());

        CategoryProductResDTO categoryProductResDTO = new CategoryProductResDTO();
        if(product.getCategoryProducts()== null)
            productDTO.setCategoryProductResDTO(null);
        else {
            categoryProductResDTO.setName(product.getCategoryProducts().getName());
            categoryProductResDTO.setSlug(product.getCategoryProducts().getSlug());
            categoryProductResDTO.setSlug(product.getCategoryProducts().getDescription());
            productDTO.setCategoryProductResDTO(categoryProductResDTO);
        }

        ProductStockResDTO productStockResDTO = new ProductStockResDTO();
        if(product.getProductsStock() == null)
            productDTO.setProductStockResDTO(null);
        else {
            productStockResDTO.setQuantity(product.getProductsStock().getQuantity());
            productDTO.setProductStockResDTO(productStockResDTO);
        }

        return productDTO;
    }

    @Override
    public Product addProduct(ProductReqDTO productReqDTO) {
        final Slugify slg = Slugify.builder().build();
        Product product = new Product();
        product.setName(productReqDTO.name());
        product.setDescription(productReqDTO.description());
        product.setPrice(productReqDTO.price());

        product.setCreatedAt(new Date());
        product.setSlug(slg.slugify(productReqDTO.name()));
        product.setStatus(true);

        ProductsStock productsStock = new ProductsStock();
        productsStock.setQuantity(productReqDTO.productStockReqDTO().quantity());
        productsStock.setStatus(true);
        productsStock.setCreatedAt(new Date());
        product.setProductsStock(this.productsStockRepo.save(productsStock));

        return this.productRepo.save(product);
    }

    @Override
    public ProductResDTO getProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Product> productDTOPage = this.productRepo.findAll(pageable);
        List<ProductDTO> content = productDTOPage.stream()
                .map(product -> this.mapToProductDTO(product)).toList();

        ProductResDTO productResDTO = new ProductResDTO();
        productResDTO.setContent(content);
        productResDTO.setPageNumber(productDTOPage.getNumber());
        productResDTO.setPageSize(productDTOPage.getSize());
        productResDTO.setTotalElements(productDTOPage.getTotalElements());
        productResDTO.setTotalPages(productResDTO.getTotalPages());
        productResDTO.setLast(productDTOPage.isLast());
        return productResDTO;

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
