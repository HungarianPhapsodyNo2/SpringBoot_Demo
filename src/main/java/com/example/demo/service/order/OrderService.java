package com.example.demo.service.order;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.model.ShoppingCartItem;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.request.CheckOutShoppingCartRequest;
import com.example.demo.service.product.IProductService;
import com.example.demo.service.shopping_cart.IShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final IProductService productService;
    private final IShoppingCartService shoppingCartService;

    @Override
    public Order createOrderByProduct(Long productId, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        try {
            Product product = productService.getProduct(productId);

            Order order = new Order();
            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(quantity)
                    .unitPrice(product.getPrice())
                    .build();
            order.addItem(orderItem);

            return orderRepository.save(order);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Product not found: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating the order: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public Order createOrderFromShoppingCart(Long shoppingCartId, CheckOutShoppingCartRequest request) {
        List<ShoppingCartItem> selectedCartItems = request.getSelectedProductIds().stream()
                .map(productId -> shoppingCartService.getShoppingCartItem(shoppingCartId, productId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("A product was not found in the shopping cart")))
                .toList();

        Order order = new Order();
        selectedCartItems.forEach(cartItem -> {
            OrderItem orderItem = OrderItem.builder()
                    .product(cartItem.getProduct())
                    .quantity(cartItem.getQuantity())
                    .unitPrice(cartItem.getProduct().getPrice())
                    .build();
            order.addItem(orderItem);
            shoppingCartService.removeItemFromShoppingCart(shoppingCartId, cartItem.getProduct().getId());
        });

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void removeOrderItem(Long orderId, Long productId) {
        Order order = getOrder(orderId);

        OrderItem orderItem = getOrderItem(orderId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not found"));
        order.removeItem(orderItem);

        orderRepository.save(order);
    }

    @Override
    public BigDecimal getSubtotal(Long orderId) {
        Order order = getOrder(orderId);

        return order.getSubtotal();
    }

    @Override
    public Optional<OrderItem> getOrderItem(Long orderId, Long productId) {
        return orderItemRepository.findByOrderIdAndProductId(orderId, productId);
    }
}