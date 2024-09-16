package com.example.demo.repository;

import com.example.demo.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    // The unique constraint ensures that there is at most one shopping cart item for a given shopping cart and product.
    Optional<ShoppingCartItem> findByShoppingCartIdAndProductId(Long shoppingCartId, Long productId);
}
