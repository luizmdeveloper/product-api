package com.luizmariodev.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luizmariodev.domain.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
