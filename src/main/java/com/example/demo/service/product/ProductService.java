package com.example.demo.service.product;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.request.AddProductRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Product addProduct(AddProductRequest addProductRequest) {
        if (addProductRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        Product product = Product.builder()
                .name(addProductRequest.getName())
                .price(addProductRequest.getPrice())
                .description(addProductRequest.getDescription())
                .build();
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public Product updateProduct(Long productId, JsonNode productPatch) {
        Product product = getProduct(productId);

        try {
            JsonNode productJson = objectMapper.convertValue(product, JsonNode.class);
            ((ObjectNode) productJson).setAll((ObjectNode) productPatch);
            Product updatedProduct = objectMapper.convertValue(productJson, Product.class);
            return productRepository.save(updatedProduct);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product data: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the product: " + e.getMessage());
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}