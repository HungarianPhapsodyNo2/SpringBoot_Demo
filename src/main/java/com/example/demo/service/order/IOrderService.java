package com.example.demo.service.order;

import com.example.demo.model.Order;
import com.example.demo.request.CreateOrderRequest;

import java.util.List;

public interface IOrderService {

    Order createOrder(CreateOrderRequest createOrderRequest);

    List<Order> getAllOrders();

    // other service methods
}
