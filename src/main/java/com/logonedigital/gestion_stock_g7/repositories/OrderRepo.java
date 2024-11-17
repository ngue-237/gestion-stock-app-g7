package com.logonedigital.gestion_stock_g7.repositories;

import com.logonedigital.gestion_stock_g7.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
}
