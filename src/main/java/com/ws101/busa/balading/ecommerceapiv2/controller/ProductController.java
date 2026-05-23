package com.ws101.busa.balading.ecommerceapiv2.controller;

import com.ws101.busa.balading.ecommerceapiv2.model.Product;
import com.ws101.busa.balading.ecommerceapiv2.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * REST Controller for managing product-related operations.
 *
 * Provides endpoints for creating, retrieving, updating, and deleting products.
 * Also supports filtering and searching products by category, name, and price range.
 *
 * @author T. P. Echaluce
 * @see ProductService
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Constructs a ProductController with the required ProductService dependency.
     *
     * @param productService the service layer for product operations
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves all products in the system.
     *
     * @return a ResponseEntity containing a list of all products and HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id the ID of the product to retrieve
     * @return a ResponseEntity containing the product and HTTP 200 status
     * @throws com.ws101.busa.balading.ecommerceapiv2.exception.ProductNotFoundException
     *         if no product exists with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Retrieves all products belonging to a specific category.
     *
     * @param category the category name to filter by
     * @return a ResponseEntity containing matching products and HTTP 200 status
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.filterByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Searches products by name using a partial match.
     *
     * @param name the name or partial name to search for
     * @return a ResponseEntity containing matching products and HTTP 200 status
     */
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        List<Product> products = productService.filterByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Filters products within a specified price range.
     *
     * @param minPrice the minimum price inclusive
     * @param maxPrice the maximum price inclusive
     * @return a ResponseEntity containing products within the price range and HTTP 200 status
     * @throws IllegalArgumentException if minPrice is greater than maxPrice
     */
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<Product> products = productService.filterByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Creates a new product in the system.
     *
     * @param product the product to create. Must pass validation constraints
     * @return a ResponseEntity containing the created product and HTTP 201 status
     * @throws org.springframework.web.bind.MethodArgumentNotValidException
     *         if validation fails
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    /**
     * Updates an existing product completely via PUT.
     *
     * Replaces all fields of the product with the provided data.
     *
     * @param id the ID of the product to update
     * @param product the new product data
     * @return a ResponseEntity containing the updated product and HTTP 200 status
     * @throws com.ws101.busa.balading.ecommerceapiv2.exception.ProductNotFoundException
     *         if no product exists with the given ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    /**
     * Partially updates an existing product via PATCH.
     *
     * Only updates the fields that are non-null in the request body.
     *
     * @param id the ID of the product to update
     * @param product the product fields to update
     * @return a ResponseEntity containing the updated product and HTTP 200 status
     * @throws com.ws101.busa.balading.ecommerceapiv2.exception.ProductNotFoundException
     *         if no product exists with the given ID
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productService.getProductById(id);
        if (product.getName() != null) {
            existingProduct.setName(product.getName());
        }
        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }
        if (product.getCategory() != null) {
            existingProduct.setCategory(product.getCategory());
        }
        if (product.getStockQuantity() != null) {
            existingProduct.setStockQuantity(product.getStockQuantity());
        }
        if (product.getImageUrl() != null) {
            existingProduct.setImageUrl(product.getImageUrl());
        }
        Product updatedProduct = productService.createProduct(existingProduct);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return a ResponseEntity with HTTP 204 No Content status
     * @throws com.ws101.busa.balading.ecommerceapiv2.exception.ProductNotFoundException
     *         if no product exists with the given ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}