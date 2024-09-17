package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Generic API response wrapper.
 *
 * @param <T> the type of the response data
 */
@Data
@AllArgsConstructor
public class ApiResponse<T> {

    /**
     * The message associated with the response.
     */
    private String message;

    /**
     * The data associated with the response.
     */
    private T data;
}