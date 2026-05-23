package com.ws101.busa.balading.ecommerceapiv2.service;

import com.ws101.busa.balading.ecommerceapiv2.exception.ProductNotFoundException;
import com.ws101.busa.balading.ecommerceapiv2.model.Product;
import com.ws101.busa.balading.ecommerceapiv2.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

/**
 * Service class for product-related business logic.
 *
 * Handles product operations including retrieval, filtering, creation, updating,
 * and deletion. Acts as an intermediary between the controller layer and the
 * repository layer.
 *
 * @author T. P. Echaluce
 * @see ProductRepository
 * @see Product
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Constructs a ProductService with the required ProductRepository dependency.
     *
     * @param productRepository the repository for product data access
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all products from the data source.
     *
     * @return a list of all products. Returns an empty list if no products exist
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id the ID of the product to retrieve
     * @return the product if found
     * @throws ProductNotFoundException if no product exists with the given ID
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    /**
     * Filters products by category.
     *
     * @param category the category name to filter by
     * @return a list of products in the specified category
     */
    public List<Product> filterByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    /**
     * Searches products by name using case-insensitive partial matching.
     *
     * @param name the name or partial name to search for
     * @return a list of products matching the search criteria
     */
    public List<Product> filterByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Filters products within a specified price range.
     *
     * @param minPrice the minimum price inclusive
     * @param maxPrice the maximum price inclusive
     * @return a list of products within the price range
     * @throws IllegalArgumentException if minPrice is greater than maxPrice
     */
    public List<Product> filterByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Creates and persists a new product.
     *
     * @param product the product to create
     * @return the saved product with generated ID
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates an existing product completely.
     *
     * Replaces all fields of the existing product with the provided data.
     *
     * @param id the ID of the product to update
     * @param product the new product data
     * @return the updated product
     * @throws ProductNotFoundException if no product exists with the given ID
     */
    public Product updateProduct(Long id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setCategory(product.getCategory());
        existing.setStockQuantity(product.getStockQuantity());
        existing.setImageUrl(product.getImageUrl());
        return productRepository.save(existing);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @throws ProductNotFoundException if no product exists with the given ID
     */
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }
}