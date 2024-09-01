package com.example.demo.service.product;

import com.example.demo.model.Product;

import java.util.List;

public interface IProductService {

    Product addProduct(String name, Double price, String description);

    List<Product> getAllProducts();

    // other service methods
}
