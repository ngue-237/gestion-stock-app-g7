package com.logonedigital.gestion_stock_g7.repositories;

import com.logonedigital.gestion_stock_g7.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.status = true")
    List<Product> fetchActiveProduct();
}
