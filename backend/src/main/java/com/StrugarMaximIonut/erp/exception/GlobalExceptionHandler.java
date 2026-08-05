package com.StrugarMaximIonut.erp.exception;

import com.StrugarMaximIonut.erp.exception.bill.BillNotFoundException;
import com.StrugarMaximIonut.erp.exception.bill.NoBillsFoundException;
import com.StrugarMaximIonut.erp.exception.client.ClientFoundException;
import com.StrugarMaximIonut.erp.exception.client.ClientNotFoundException;
import com.StrugarMaximIonut.erp.exception.client.NoClientsException;
import com.StrugarMaximIonut.erp.exception.orders.NoOrdersException;
import com.StrugarMaximIonut.erp.exception.orders.OrderCancelledException;
import com.StrugarMaximIonut.erp.exception.orders.OrderNotFoundException;
import com.StrugarMaximIonut.erp.exception.orders.InsuficientStock;
import com.StrugarMaximIonut.erp.exception.products.NoProductsException;
import com.StrugarMaximIonut.erp.exception.products.ProductFoundException;
import com.StrugarMaximIonut.erp.exception.products.ProductNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Handle Client exceptions
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

    //Handle Products exceptions
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

    //Handle Orders exceptions
    @ExceptionHandler(NoOrdersException.class)
    public ResponseEntity<Object> noOrdersInDataBase(NoOrdersException ex){
        ApiError apiError = new ApiError(404, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> orderNotFoundInDataBase(OrderNotFoundException ex){
        ApiError apiError = new ApiError(404, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsuficientStock.class)
    public ResponseEntity<Object> productOutOfStock(InsuficientStock ex){
        ApiError apiError = new ApiError(409, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrderCancelledException.class)
    public ResponseEntity<Object> orderAlreadyCancelled(OrderCancelledException ex){
        ApiError apiError = new ApiError(409, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    //Handle Bill exceptions
    @ExceptionHandler(NoBillsFoundException.class)
    public ResponseEntity<Object> noBillsFound(NoBillsFoundException ex){
        ApiError apiError = new ApiError(404, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BillNotFoundException.class)
    public ResponseEntity<Object> billNotFound(BillNotFoundException ex){
        ApiError apiError = new ApiError(404, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
