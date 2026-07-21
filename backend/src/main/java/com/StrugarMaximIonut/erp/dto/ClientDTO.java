package com.StrugarMaximIonut.erp.dto;

public record ClientDTO (
        Integer clientID,
        String clientName,
        String clientEmail,
        String clientAddress,
        String clientPhoneNumber
) {
}
