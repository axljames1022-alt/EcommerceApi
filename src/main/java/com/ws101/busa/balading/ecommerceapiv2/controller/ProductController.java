package com.ws101.busa.balading.ecommerceapiv2.controller;
import com.ws101.busa.balading.ecommerceapiv2.dto.CreateProductDto;
import com.ws101.busa.balading.ecommerceapiv2.dto.ProductListingEntry;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @GetMapping
    public ResponseEntity<List<ProductListingEntry>> getAllProducts() {
        // example response lang to
        List<ProductListingEntry> products = List.of(
                new ProductListingEntry(1L, "Product 1", 123.123)
        );
        return ResponseEntity.ok(products);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductDto dto) {
        return ResponseEntity.ok("Product created: " + dto.prodName());
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok("Product deleted");
    }
}