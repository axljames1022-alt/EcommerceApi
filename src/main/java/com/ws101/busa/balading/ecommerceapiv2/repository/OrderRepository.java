package com.ws101.busa.balading.ecommerceapiv2.repository;

import com.ws101.busa.balading.ecommerceapiv2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByOrderDateBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);
}
