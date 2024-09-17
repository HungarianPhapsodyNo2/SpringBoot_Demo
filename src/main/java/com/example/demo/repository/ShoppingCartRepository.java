package com.example.demo.repository;

import com.example.demo.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for ShoppingCart entities.
 * Provides methods to perform CRUD operations and custom queries.
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}