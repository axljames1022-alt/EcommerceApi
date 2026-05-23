package com.ws101.busa.balading.ecommerceapiv2.repository;

import com.ws101.busa.balading.ecommerceapiv2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Method Naming: check if category name exists
    Optional<Category> findByName(String name);

    boolean existsByName(String name);
}