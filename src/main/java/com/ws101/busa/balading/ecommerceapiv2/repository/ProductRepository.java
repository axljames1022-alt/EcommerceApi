package com.ws101.busa.balading.ecommerceapiv2.repository;

import com.ws101.busa.balading.ecommerceapiv2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
}