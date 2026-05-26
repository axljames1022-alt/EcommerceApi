package com.ws101.busa.balading.ecommerceapiv2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        // kahit sino logged in pwede dito
        return ResponseEntity.ok("List of products");
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@RequestBody Object product) {
        // ADMIN lang pwede gumawa
        return ResponseEntity.ok("Product created");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        // ADMIN lang pwede mag-delete
        return ResponseEntity.ok("Product deleted");
    }
}