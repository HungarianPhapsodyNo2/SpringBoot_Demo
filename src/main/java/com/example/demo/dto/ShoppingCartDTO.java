package com.example.demo.dto;

import com.example.demo.model.ShoppingCart;
import com.example.demo.model.ShoppingCartItem;
import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object for ShoppingCart.
 * Contains shopping cart details such as id and items.
 */
@Data
public class ShoppingCartDTO {

    private Long id;
    private List<ShoppingCartItemDTO> items;

    public ShoppingCartDTO(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
        this.items = shoppingCart.getItems().stream().map(ShoppingCartItemDTO::new).toList();
    }

    /**
     * Data Transfer Object for ShoppingCartItem.
     * Contains product details and quantity.
     */
    @Data
    public static class ShoppingCartItemDTO {

        private ProductDTO product;
        private Integer quantity;

        public ShoppingCartItemDTO(ShoppingCartItem shoppingCartItem) {
            this.product = new ProductDTO(shoppingCartItem.getProduct());
            this.quantity = shoppingCartItem.getQuantity();
        }
    }
}