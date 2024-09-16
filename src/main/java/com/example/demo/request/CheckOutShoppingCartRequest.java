package com.example.demo.request;

import lombok.Data;

import java.util.List;

@Data
public class CheckOutShoppingCartRequest {

    private List<Long> selectedProductIds;
}
