package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing an order.
 * Contains order items and calculates the subtotal.
 */

@Entity
@Table(name = "`order`")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> items;

    @Column(nullable = false)
    private BigDecimal subtotal;

    public Order() {
        this.items = new HashSet<>();
        this.subtotal = BigDecimal.ZERO;
    }

    /**
     * Adds an item to the order and updates the subtotal.
     *
     * @param item the item to add
     */
    public void addItem(OrderItem item) {
        this.items.add(item);
        item.setOrder(this);
        this.subtotal = this.subtotal.add(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
    }

    /**
     * Removes an item from the order and updates the subtotal.
     *
     * @param item the item to remove
     */
    public void removeItem(OrderItem item) {
        this.items.remove(item);
        item.setOrder(null);
        this.subtotal = this.subtotal.subtract(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
    }
}