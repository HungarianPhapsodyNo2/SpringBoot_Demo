package com.example.demo.service.order;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.request.CheckOutShoppingCartRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Interface for Order Service.
 * Provides methods to manage orders and their items.
 */
public interface IOrderService {

    /**
     * Creates an order for a specific product.
     *
     * @param productId the ID of the product
     * @param quantity  the quantity of the product
     * @return the created Order
     */
    Order createOrderByProduct(Long productId, Integer quantity);

    /**
     * Creates an order from a shopping cart.
     *
     * @param shoppingCartId the ID of the shopping cart
     * @param request        the request containing checkout details
     * @return the created Order
     */
    Order createOrderFromShoppingCart(Long shoppingCartId, CheckOutShoppingCartRequest request);

    /**
     * Retrieves all orders.
     *
     * @return a list of all orders
     */
    List<Order> getAllOrders();

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId the ID of the order
     * @return the Order with the specified ID
     */
    Order getOrder(Long orderId);

    /**
     * Deletes an order by its ID.
     *
     * @param orderId the ID of the order to be deleted
     */
    void deleteOrder(Long orderId);

    /**
     * Removes an item from an order.
     *
     * @param orderId   the ID of the order
     * @param productId the ID of the product to be removed
     */
    void removeOrderItem(Long orderId, Long productId);

    /**
     * Calculates the subtotal of an order.
     *
     * @param orderId the ID of the order
     * @return the subtotal as BigDecimal
     */
    BigDecimal getSubtotal(Long orderId);

    /**
     * Retrieves a specific item from an order.
     *
     * @param orderId   the ID of the order
     * @param productId the ID of the product
     * @return an Optional containing the OrderItem if found, otherwise empty
     */
    Optional<OrderItem> getOrderItem(Long orderId, Long productId);

    // other service methods
}