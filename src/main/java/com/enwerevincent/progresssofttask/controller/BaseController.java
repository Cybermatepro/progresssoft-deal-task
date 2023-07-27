package com.enwerevincent.progresssofttask.controller;


import com.enwerevincent.progresssofttask.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    public <T> ResponseEntity<ApiResponse<T>> getResponse(String message, HttpStatus status, T data){
        return new ResponseEntity<>(new ApiResponse<>(message, status, data), HttpStatus.OK);
    }

    public <T> ResponseEntity<ApiResponse<T>> getErrorResponse(String message, HttpStatus status, T data){
        return new ResponseEntity<>(new ApiResponse<>(message, status, data), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public <T> ResponseEntity<ApiResponse<T>> getResponse(T data){
        return new ResponseEntity<>(new ApiResponse<>("success", HttpStatus.CREATED, data), HttpStatus.CREATED);
    }
}
