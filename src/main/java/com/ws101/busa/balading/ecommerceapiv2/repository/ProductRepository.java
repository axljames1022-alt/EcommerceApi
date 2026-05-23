package com.ws101.busa.balading.ecommerceapiv2.repository;

import com.ws101.busa.balading.ecommerceapiv2.model.Category;
import com.ws101.busa.balading.ecommerceapiv2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Method Naming: finder method
    List<Product> findByCategory(Category category);

    List<Product> findByNameContainingIgnoreCase(String name);

    // @Query with JPQL: custom filter for price range
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findByPriceBetween(@Param("min") Double min, @Param("max") Double max);
}