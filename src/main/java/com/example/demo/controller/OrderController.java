package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.request.CreateOrderRequest;
import com.example.demo.model.*;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<OrderDTO>> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        try {
            Order order = orderService.createOrder(createOrderRequest);
            return ResponseEntity.ok(new ApiResponse<>("Order created successfully", new OrderDTO(order)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderDTO> orderDTOs = orders.stream().map(OrderDTO::new).toList();
        return ResponseEntity.ok(new ApiResponse<>("Orders retrieved successfully", orderDTOs));
    }

    // Other endpoints
}
