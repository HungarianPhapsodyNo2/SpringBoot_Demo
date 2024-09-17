package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Order entities.
 * Provides methods to perform CRUD operations and custom queries.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}