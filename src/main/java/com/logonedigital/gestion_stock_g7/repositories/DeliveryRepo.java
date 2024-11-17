package com.logonedigital.gestion_stock_g7.repositories;

import com.logonedigital.gestion_stock_g7.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepo extends JpaRepository<Delivery,Integer> {
}
