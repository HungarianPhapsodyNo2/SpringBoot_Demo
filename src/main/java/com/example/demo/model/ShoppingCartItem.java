package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing an item in a shopping cart.
 * Contains references to the shopping cart and product, as well as quantity.
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"shopping_cart_id", "product_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
}