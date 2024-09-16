package com.example.demo.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {

    private String name;

    private BigDecimal price;

    private String description;
}
