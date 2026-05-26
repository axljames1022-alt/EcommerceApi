package com.ws101.busa.balading.ecommerceapiv2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public String createOrder() {
        return "Order created";
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllOrders() {
        return "All orders for admin";
    }
}