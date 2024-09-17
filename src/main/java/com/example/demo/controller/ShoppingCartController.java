package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ShoppingCartDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Order;
import com.example.demo.model.ShoppingCart;
import com.example.demo.request.AddCartItemRequest;
import com.example.demo.request.CheckOutShoppingCartRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.order.IOrderService;
import com.example.demo.service.shopping_cart.IShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * REST controller for managing shopping carts.
 * Provides endpoints for creating, retrieving, updating, and deleting shopping carts and their items.
 */
@RestController
@RequestMapping("/api/shopping_cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final IShoppingCartService shoppingCartService;
    private final IOrderService orderService;

    /**
     * This endpoint should not exist. It's here for demonstration purposes only.
     * By design, the shopping cart should be created with the user it belongs to.
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ShoppingCartDTO>> createShoppingCart() {
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart();
        return ResponseEntity.ok(new ApiResponse<>("Shopping cart created successfully", new ShoppingCartDTO(shoppingCart)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShoppingCartDTO>> getShoppingCartById(@PathVariable Long id) {
        try {
            ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(id);
            return ResponseEntity.ok(new ApiResponse<>("Shopping cart retrieved successfully", new ShoppingCartDTO(shoppingCart)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    @PostMapping("/{id}/add_item")
    public ResponseEntity<ApiResponse<String>> addItemToShoppingCart(@PathVariable Long id, @RequestBody AddCartItemRequest addCartItemRequest) {
        try {
            shoppingCartService.addItemToShoppingCart(id, addCartItemRequest);
            return ResponseEntity.ok(new ApiResponse<>("Product added to cart successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}/items/{productId}")
    public ResponseEntity<ApiResponse<String>> removeShoppingCartItem(@PathVariable Long id, @PathVariable Long productId) {
        try {
            shoppingCartService.removeItemFromShoppingCart(id, productId);
            return ResponseEntity.ok(new ApiResponse<>("Item removed from shopping cart successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}/items/{productId}/update_quantity")
    public ResponseEntity<ApiResponse<String>> updateQuantity(@PathVariable Long id, @PathVariable Long productId, @RequestParam Integer quantity) {
        try {
            shoppingCartService.updateItemQuantity(id, productId, quantity);
            return ResponseEntity.ok(new ApiResponse<>("Item quantity updated successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    @GetMapping("/{id}/subtotal")
    public ResponseEntity<ApiResponse<BigDecimal>> getSubtotal(@PathVariable Long id) {
        try {
            BigDecimal subtotal = shoppingCartService.calculateSubtotal(id);
            return ResponseEntity.ok(new ApiResponse<>("Total price calculated successfully", subtotal));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    @PostMapping("/{id}/checkout")
    public ResponseEntity<ApiResponse<OrderDTO>> checkout(@PathVariable Long id, @RequestBody CheckOutShoppingCartRequest checkOutShoppingCartRequest) {
        try {
            Order order = orderService.createOrderFromShoppingCart(id, checkOutShoppingCartRequest);
            return ResponseEntity.ok(new ApiResponse<>("Order created successfully", new OrderDTO(order)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    @PostMapping("/{id}/clear")
    public ResponseEntity<ApiResponse<String>> clearShoppingCart(@PathVariable Long id) {
        try {
            shoppingCartService.clearShoppingCart(id);
            return ResponseEntity.ok(new ApiResponse<>("Shopping cart cleared successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }
}