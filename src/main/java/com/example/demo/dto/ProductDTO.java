package com.example.demo.dto;

import com.example.demo.model.Product;
import lombok.*;

@Data
public class ProductDTO {

    private String name;

    private Double price;

    private String description;

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }
}
