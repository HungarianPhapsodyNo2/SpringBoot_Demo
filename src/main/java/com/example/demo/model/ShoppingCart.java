package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "product_id")
    private Set<ShoppingCartItem> items;

    public ShoppingCart() {
        this.items = new HashSet<>();
    }

    public void addItem(ShoppingCartItem item) {
        this.items.add(item);
        item.setShoppingCart(this);
    }

    public void removeItem(ShoppingCartItem item) {
        this.items.remove(item);
        item.setShoppingCart(null);
    }
}
