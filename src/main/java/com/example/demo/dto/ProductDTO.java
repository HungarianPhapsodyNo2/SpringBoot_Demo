package com.example.demo.dto;

import com.example.demo.model.Product;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private Double price;

    private String description;

    public ProductDTO(@NotNull Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }
}
