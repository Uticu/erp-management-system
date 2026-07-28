package com.StrugarMaximIonut.erp.exception.orders;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
