package com.example.demo.dto;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;

    private List<OrderItemDTO> items;

    private BigDecimal subTotal;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.items = order.getItems().stream().map(OrderItemDTO::new).toList();
        this.subTotal = order.getSubTotal();
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
