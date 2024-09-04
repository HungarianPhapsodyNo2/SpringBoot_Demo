package com.example.demo.request;

import lombok.*;

@Data
public class AddProductRequest {

    private String name;

    private Double price;

    private String description;
}
