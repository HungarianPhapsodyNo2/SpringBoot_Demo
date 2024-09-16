package com.example.demo.service.shopping_cart;

import com.example.demo.model.ShoppingCart;
import com.example.demo.model.ShoppingCartItem;
import com.example.demo.request.AddCartItemRequest;

import java.math.BigDecimal;
import java.util.Optional;

public interface IShoppingCartService {

    ShoppingCart createShoppingCart();

    ShoppingCart getShoppingCart(Long shoppingCartId);

    void addItemToShoppingCart(Long shoppingCartId, AddCartItemRequest addCartItemRequest);

    void removeItemFromShoppingCart(Long shoppingCartId, Long productId);

    void updateItemQuantity(Long shoppingCartId, Long productId, Integer quantity);

    BigDecimal calculateSubtotal(Long shoppingCartId);

    void clearShoppingCart(Long shoppingCartId);

    Optional<ShoppingCartItem> getShoppingCartItem(Long shoppingCartId, Long productId);

    // other service methods
}
