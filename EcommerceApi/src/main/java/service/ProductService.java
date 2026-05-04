package com.ws101.busa.balading.EcommerceApi.service;

import com.ws101.busa.balading.EcommerceApi.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private List<Product> productList = new ArrayList<>();
    private Long counter = 1L;

    public ProductService() {
        // sample data (at least 10)
        for (int i = 1; i <= 10; i++) {
            productList.add(new Product(counter++, "Product " + i,
                    "Description " + i, 100 + i, "Category",
                    10, null));
        }
    }

    // GET ALL
    public List<Product> getAllProducts() {
        return productList;
    }

    // GET BY ID
    public Product getProductById(Long id) {
        return productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // CREATE
    public Product createProduct(Product product) {
        product.setId(counter++);
        productList.add(product);
        return product;
    }

    // UPDATE (PUT)
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);

        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setCategory(updatedProduct.getCategory());
        existing.setStockQuantity(updatedProduct.getStockQuantity());

        return existing;
    }

    // PATCH
    public Product patchProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);

        if (updatedProduct.getName() != null)
            existing.setName(updatedProduct.getName());

        if (updatedProduct.getPrice() != 0)
            existing.setPrice(updatedProduct.getPrice());

        return existing;
    }

    // DELETE
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productList.remove(product);
    }

    // FILTER
    public List<Product> filterProducts(String type, String value) {
        return productList.stream()
                .filter(p -> {
                    if (type.equalsIgnoreCase("name"))
                        return p.getName().contains(value);
                    if (type.equalsIgnoreCase("category"))
                        return p.getCategory().equalsIgnoreCase(value);
                    return false;
                })
                .collect(Collectors.toList());
    }
}