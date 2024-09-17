package com.example.demo.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Request object for adding a new product.
 * Format as: { "name": "Product Name", "price": 9.99, "description": "Product Description" }
 */
@Data
public class AddProductRequest {

    /**
     * The name of the product.
     */
    private String name;

    /**
     * The price of the product.
     */
    private BigDecimal price;

    /**
     * The description of the product.
     */
    private String description;
}