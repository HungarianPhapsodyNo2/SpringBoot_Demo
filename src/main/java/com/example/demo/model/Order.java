package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> items;

    @Column(nullable = false)
    private BigDecimal subTotal;

    public Order() {
        this.items = new HashSet<>();
        this.subTotal = BigDecimal.ZERO;
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
        item.setOrder(this);
        this.subTotal = this.subTotal.add(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
    }

    public void removeItem(OrderItem item) {
        this.items.remove(item);
        item.setOrder(null);
        this.subTotal = this.subTotal.subtract(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
    }
}
