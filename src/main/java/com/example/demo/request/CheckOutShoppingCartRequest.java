package com.example.demo.request;

import lombok.Data;

import java.util.List;

/**
 * Request object for checking out a shopping cart.
 * Format as: { "selectedProductIds": [1, 2, 3] }
 */
@Data
public class CheckOutShoppingCartRequest {

    /**
     * List of selected product IDs for checkout.
     */
    private List<Long> selectedProductIds;
}