package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Product entities.
 * Provides methods to perform CRUD operations and custom queries.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}