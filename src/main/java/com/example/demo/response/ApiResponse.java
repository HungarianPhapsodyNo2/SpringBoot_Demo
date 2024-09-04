package com.example.demo.response;

import lombok.*;
@Data
@AllArgsConstructor
public class ApiResponse {

        private String message;

        private Object data;
}
