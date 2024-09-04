package com.example.demo.dto;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import lombok.*;

import java.util.List;

@Data
public class OrderDTO {

    private List<OrderItemDTO> orderItemDTOs;

    public OrderDTO(Order order) {
        this.orderItemDTOs = order.getItems().stream().map(OrderItemDTO::new).toList();
    }

    @Data
    public static class OrderItemDTO {

        private ProductDTO productDTO;

        private Integer quantity;

        public OrderItemDTO(OrderItem orderItem) {
            this.productDTO = new ProductDTO(orderItem.getProduct());
            this.quantity = orderItem.getQuantity();
        }
    }
}
