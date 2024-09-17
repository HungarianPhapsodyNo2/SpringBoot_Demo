package com.example.demo.dto;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Data Transfer Object for Order.
 * Contains order details such as id, items, and subtotal.
 */
@Data
public class OrderDTO {

    private Long id;
    private List<OrderItemDTO> items;
    private BigDecimal subtotal;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.items = order.getItems().stream().map(OrderItemDTO::new).toList();
        this.subtotal = order.getSubtotal();
    }

    /**
     * Data Transfer Object for OrderItem.
     * Contains product details and quantity.
     */
    @Data
    public static class OrderItemDTO {

        private ProductDTO product;
        private Integer quantity;

        public OrderItemDTO(OrderItem orderItem) {
            this.product = new ProductDTO(orderItem.getProduct());
            this.quantity = orderItem.getQuantity();
        }
    }
}