package com.StrugarMaximIonut.erp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoClientsException.class)
    public ResponseEntity<Object> noClientsInDataBase(NoClientsException ex){
        ApiError apiError = new ApiError(404, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Object> clientNotFoundInDataBase(ClientNotFoundException ex){
        ApiError apiError = new ApiError(404, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientFoundException.class)
    public ResponseEntity<Object> clientAlreadyInDataBase(ClientFoundException ex){
        ApiError apiError = new ApiError(409, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoProductsException.class)
    public ResponseEntity<Object> noProductsInDataBase(NoProductsException ex){
        ApiError apiError = new ApiError(404, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> productNotFoundInDataBase(ProductNotFoundException ex){
        ApiError apiError = new ApiError(404, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductFoundException.class)
    public ResponseEntity<Object> productFoundInDataBase(ProductFoundException ex){
        ApiError apiError = new ApiError(409, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }
}
