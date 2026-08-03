package com.StrugarMaximIonut.erp.exception.orders;

public class InsuficientStock extends RuntimeException {
    public InsuficientStock(String message) {
        super(message);
    }
}
