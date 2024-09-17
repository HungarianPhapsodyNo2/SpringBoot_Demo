package com.example.demo.request;

import lombok.Data;

/**
 * Request object for adding an item to the shopping cart.
 * Format as: { "productId": 1, "quantity": 2 }
 */
@Data
public class AddCartItemRequest {

    /**
     * The ID of the product to be added to the cart.
     */
    private Long productId;

    /**
     * The quantity of the product to be added to the cart.
     */
    private Integer quantity;
}