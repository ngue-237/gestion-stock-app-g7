package com.logonedigital.gestion_stock_g7.repositories;

import com.logonedigital.gestion_stock_g7.entities.Activation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivationRepo extends JpaRepository<Activation,Integer> {
    Optional<Activation> findByCode(String code);
}
