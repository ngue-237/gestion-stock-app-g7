package com.logonedigital.gestion_stock_g7.repositories;

import com.logonedigital.gestion_stock_g7.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    Optional<Customer> findByEmail(String email);

    @Query("select c from Customer c where c.email = :email")
    Optional<Customer> fetchByEmail(@Param("email") String email);
}
