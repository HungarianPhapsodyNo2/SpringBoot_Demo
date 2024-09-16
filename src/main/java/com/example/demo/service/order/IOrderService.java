package com.example.demo.service.order;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.request.CheckOutShoppingCartRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IOrderService {

    // would consider eventually migrating it to CheckOutService
    Order createOrderByProduct(Long productId, Integer quantity);

    // would consider eventually migrating it to CheckOutService
    Order createOrderFromShoppingCart(Long shoppingCartId, CheckOutShoppingCartRequest request);

    List<Order> getAllOrders();

    Order getOrder(Long orderId);

    void deleteOrder(Long orderId);

    void removeOrderItem(Long orderId, Long productId);

    BigDecimal getSubtotal(Long orderId);

    Optional<OrderItem> getOrderItem(Long orderId, Long productId);

    // other service methods
}
