package com.example.demo.repository;

import com.example.demo.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // The unique constraint ensures that there is at most one order item for a given order and product.
    Optional<OrderItem> findByOrderIdAndProductId(Long orderId, Long productId);
}
