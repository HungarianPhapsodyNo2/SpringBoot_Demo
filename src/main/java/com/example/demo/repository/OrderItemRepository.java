package com.example.demo.repository;

import com.example.demo.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for OrderItem entities.
 * Provides methods to perform CRUD operations and custom queries.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    /**
     * Finds an OrderItem by order ID and product ID.
     *
     * @param orderId   the ID of the order
     * @param productId the ID of the product
     * @return an Optional containing the OrderItem if found, otherwise empty
     */
    Optional<OrderItem> findByOrderIdAndProductId(Long orderId, Long productId);
}