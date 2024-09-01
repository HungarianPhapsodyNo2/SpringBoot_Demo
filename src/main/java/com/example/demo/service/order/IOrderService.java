package com.example.demo.service.order;

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.model.Order;

import java.util.List;

public interface IOrderService {

    Order createOrder(List<OrderItemDTO> orderItems);

    List<Order> getAllOrders();

    // other service methods
}
