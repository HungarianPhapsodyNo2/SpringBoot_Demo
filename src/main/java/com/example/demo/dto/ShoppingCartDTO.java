package com.example.demo.dto;

import com.example.demo.model.ShoppingCart;
import com.example.demo.model.ShoppingCartItem;
import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDTO {

    private Long id;

    private List<ShoppingCartItemDTO> items;

    public ShoppingCartDTO(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
        this.items = shoppingCart.getItems().stream().map(ShoppingCartItemDTO::new).toList();
    }

    @Data
    public static class ShoppingCartItemDTO {

        private ProductDTO productDTO;

        private Integer quantity;

        public ShoppingCartItemDTO(ShoppingCartItem shoppingCartItem) {
            this.productDTO = new ProductDTO(shoppingCartItem.getProduct());
            this.quantity = shoppingCartItem.getQuantity();
        }
    }
}
