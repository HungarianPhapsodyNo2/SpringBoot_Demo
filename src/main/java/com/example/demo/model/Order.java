package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addOrderItem(OrderItem item){
        this.items.add(item);
        item.setOrder(this);
    }

    public void clearOrderItems(){
        for (OrderItem item : items){
            item.setOrder(null);
        }
        items.clear();
    }

}
