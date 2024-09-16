package com.example.demo.request;

import lombok.Data;

@Data
public class AddCartItemRequest {

    private Long productId;

    private Integer quantity;
}
