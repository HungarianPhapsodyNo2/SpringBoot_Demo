package com.example.demo.service.shopping_cart;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.ShoppingCartItem;
import com.example.demo.repository.ShoppingCartItemRepository;
import com.example.demo.repository.ShoppingCartRepository;
import com.example.demo.request.AddCartItemRequest;
import com.example.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ShoppingCartService implements IShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private ProductService productService;

    @Override
    public ShoppingCart createShoppingCart() {
        return shoppingCartRepository.save(new ShoppingCart());
    }

    @Override
    public ShoppingCart getShoppingCart(Long shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping cart not found"));
    }

    @Override
    public void addItemToShoppingCart(Long shoppingCartId, AddCartItemRequest addCartItemRequest) {
        ShoppingCart shoppingCart = getShoppingCart(shoppingCartId);

        getShoppingCartItem(shoppingCartId, addCartItemRequest.getProductId())
                .ifPresentOrElse(
                        shoppingCartItem -> shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + addCartItemRequest.getQuantity()),
                        () -> {
                            Product product = productService.getProduct(addCartItemRequest.getProductId());
                            ShoppingCartItem shoppingCartItem = ShoppingCartItem.builder()
                                    .product(product)
                                    .quantity(addCartItemRequest.getQuantity())
                                    .build();
                            shoppingCart.addItem(shoppingCartItem);
                        }
                );

        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void removeItemFromShoppingCart(Long shoppingCartId, Long productId) {
        ShoppingCart shoppingCart = getShoppingCart(shoppingCartId);
        ShoppingCartItem shoppingCartItem = getShoppingCartItem(shoppingCartId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping cart item not found"));

        shoppingCart.removeItem(shoppingCartItem);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void updateItemQuantity(Long shoppingCartId, Long productId, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        ShoppingCart shoppingCart = getShoppingCart(shoppingCartId);
        ShoppingCartItem shoppingCartItem = getShoppingCartItem(shoppingCartId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping cart item not found"));

        shoppingCartItem.setQuantity(quantity);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public BigDecimal calculateSubtotal(Long shoppingCartId) {
        ShoppingCart shoppingCart = getShoppingCart(shoppingCartId);

        return shoppingCart.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void clearShoppingCart(Long shoppingCartId) {
        ShoppingCart shoppingCart = getShoppingCart(shoppingCartId);

        shoppingCart.getItems().forEach(shoppingCart::removeItem);
        shoppingCartRepository.save(shoppingCart);
    }

    // might consider implementing this method via a query to avoid iterating over the items
    // e.g. shoppingCartItemRepository.findByShoppingCartIdAndProductId(shoppingCartId, productId)
    public Optional<ShoppingCartItem> getShoppingCartItem(Long shoppingCartId, Long productId) {
        /* deprecated
        return shoppingCart.getItems().stream()
                 .filter(item -> item.getProduct().getId().equals(productId))
                 .findFirst();
         */
        return shoppingCartItemRepository.findByShoppingCartIdAndProductId(shoppingCartId, productId);
    }
}
