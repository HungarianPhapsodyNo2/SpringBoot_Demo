package com.example.demo.service.product;

import com.example.demo.model.Product;
import com.example.demo.request.AddProductRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(AddProductRequest addProductRequest);

    List<Product> getAllProducts();

    // other service methods
}
