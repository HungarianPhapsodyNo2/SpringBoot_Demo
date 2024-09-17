package com.example.demo.repository;

import com.example.demo.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for ShoppingCartItem entities.
 * Provides methods to perform CRUD operations and custom queries.
 */
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    /**
     * Finds a ShoppingCartItem by shopping cart ID and product ID.
     *
     * @param shoppingCartId the ID of the shopping cart
     * @param productId      the ID of the product
     * @return an Optional containing the ShoppingCartItem if found, otherwise empty
     */
    Optional<ShoppingCartItem> findByShoppingCartIdAndProductId(Long shoppingCartId, Long productId);
}