package com.example.demo.dto;

import com.example.demo.model.Product;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Data Transfer Object for Product.
 * Contains product details such as id, name, price, and description.
 */
@Data
public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }
}