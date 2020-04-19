package com.luizmariodev.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luizmariodev.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
