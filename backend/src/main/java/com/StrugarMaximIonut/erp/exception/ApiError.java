package com.StrugarMaximIonut.erp.exception;

import java.time.LocalDateTime;

public record ApiError (
        Integer httpCode,
        String message,
        LocalDateTime errorTime
){
}
