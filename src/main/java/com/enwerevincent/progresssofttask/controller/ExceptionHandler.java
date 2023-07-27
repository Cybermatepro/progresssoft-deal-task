package com.enwerevincent.progresssofttask.controller;

import com.enwerevincent.progresssofttask.exception.AppCustomException;
import com.enwerevincent.progresssofttask.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.xml.bind.ValidationException;

@ControllerAdvice
@Slf4j
public class ExceptionHandler extends BaseController{


    @org.springframework.web.bind.annotation.ExceptionHandler({AppCustomException.class})
    public ResponseEntity<ApiResponse<String>> handleEx (AppCustomException ex) {
        return getErrorResponse("error", HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }


    @org.springframework.web.bind.annotation.ExceptionHandler({ValidationException.class})
    public ResponseEntity<ApiResponse<String>> handleEx (ValidationException ex) {
        return getErrorResponse("error", HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResponse<String>> handleEx (Exception ex) {
        return getErrorResponse("error", HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
