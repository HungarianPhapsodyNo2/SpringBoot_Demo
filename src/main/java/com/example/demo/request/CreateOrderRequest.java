package com.example.demo.request;

import lombok.*;

import java.util.List;

@Data
public class CreateOrderRequest {

    private List<OrderItem> orderItems;

    @Data
    public static class OrderItem {

        private Long productId;

        private Integer quantity;
    }
}
