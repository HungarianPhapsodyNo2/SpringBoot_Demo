package com.example.demo.service.shopping_cart;

import com.example.demo.model.ShoppingCart;
import com.example.demo.model.ShoppingCartItem;
import com.example.demo.request.AddCartItemRequest;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Interface for Shopping Cart Service.
 * Provides methods to manage shopping carts and their items.
 */
public interface IShoppingCartService {

    /**
     * Creates a new shopping cart.
     *
     * @return the created ShoppingCart
     */
    ShoppingCart createShoppingCart();

    /**
     * Retrieves a shopping cart by its ID.
     *
     * @param shoppingCartId the ID of the shopping cart
     * @return the ShoppingCart with the specified ID
     */
    ShoppingCart getShoppingCart(Long shoppingCartId);

    /**
     * Adds an item to a shopping cart.
     *
     * @param shoppingCartId     the ID of the shopping cart
     * @param addCartItemRequest the request containing item details to be added
     */
    void addItemToShoppingCart(Long shoppingCartId, AddCartItemRequest addCartItemRequest);

    /**
     * Removes an item from a shopping cart.
     *
     * @param shoppingCartId the ID of the shopping cart
     * @param productId      the ID of the product to be removed
     */
    void removeItemFromShoppingCart(Long shoppingCartId, Long productId);

    /**
     * Updates the quantity of an item in a shopping cart.
     *
     * @param shoppingCartId the ID of the shopping cart
     * @param productId      the ID of the product
     * @param quantity       the new quantity of the product
     */
    void updateItemQuantity(Long shoppingCartId, Long productId, Integer quantity);

    /**
     * Calculates the subtotal of a shopping cart.
     *
     * @param shoppingCartId the ID of the shopping cart
     * @return the subtotal as BigDecimal
     */
    BigDecimal calculateSubtotal(Long shoppingCartId);

    /**
     * Clears all items from a shopping cart.
     *
     * @param shoppingCartId the ID of the shopping cart
     */
    void clearShoppingCart(Long shoppingCartId);

    /**
     * Retrieves a specific item from a shopping cart.
     *
     * @param shoppingCartId the ID of the shopping cart
     * @param productId      the ID of the product
     * @return an Optional containing the ShoppingCartItem if found, otherwise empty
     */
    Optional<ShoppingCartItem> getShoppingCartItem(Long shoppingCartId, Long productId);

    // other service methods
}
