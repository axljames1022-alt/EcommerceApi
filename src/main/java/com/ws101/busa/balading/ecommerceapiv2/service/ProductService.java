package com.ws101.busa.balading.ecommerceapiv2.service;

import com.ws101.busa.balading.ecommerceapiv2.model.Product;
import com.ws101.busa.balading.ecommerceapiv2.model.Category;
import com.ws101.busa.balading.ecommerceapiv2.repository.ProductRepository;
import com.ws101.busa.balading.ecommerceapiv2.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getProducts(Long categoryId) {
        if (categoryId != null) {
            return productRepository.findByCategoryId(categoryId);
        }
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        System.out.println("=== DEBUG START ===");
        System.out.println("Product Name: " + product.getName());
        System.out.println("Product Price: " + product.getPrice());

        if (product.getCategory() == null || product.getCategory().getId() == null) {
            throw new IllegalArgumentException("Category is required");
        }

        Long categoryId = product.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        System.out.println("Category ID: " + category.getId());
        System.out.println("=== DEBUG END ===");

        product.setCategory(category);
        return productRepository.save(product);
    }
}