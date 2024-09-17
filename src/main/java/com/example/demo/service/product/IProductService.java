package com.example.demo.service.product;

import com.example.demo.model.Product;
import com.example.demo.request.AddProductRequest;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

/**
 * Service interface for managing products.
 */
public interface IProductService {

    /**
     * Adds a new product.
     *
     * @param addProductRequest the request containing product details
     * @return the added product
     */
    Product addProduct(AddProductRequest addProductRequest);

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    List<Product> getAllProducts();

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product to retrieve
     * @return the retrieved product
     */
    Product getProduct(Long productId);

    /**
     * Updates a product using a JSON patch.
     *
     * @param productId    the ID of the product to update
     * @param productPatch the JSON patch containing the updates
     * @return the updated product
     */
    Product updateProduct(Long productId, JsonNode productPatch);

    /**
     * Deletes a product by its ID.
     *
     * @param productId the ID of the product to delete
     */
    void deleteProduct(Long productId);

    // other service methods
}