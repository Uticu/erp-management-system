package com.StrugarMaximIonut.erp.dto.orders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record OrdersDTO (
        @NotNull(message = "orderID is mandatory")
        Integer orderID,

        @NotNull(message = "clientID is mandatory")
        Integer clientID,

        @NotBlank(message = "Date is mandatory")
        LocalDateTime orderDate,

        @NotBlank(message = "Status is mandatory")
        @Size(max = 255, message = "Status cannot exceed 255 characters")
        String orderStatus,

        @NotBlank(message = "Delivery address is mandatory")
        @Size(max = 255, message = "Delivery address cannot exceed 255 characters")
        String orderDelvieryAddress,

        @NotBlank(message = "Client phone number is mandatory")
        @Size(max = 20, message = "Client phone number cannot exceed 20 characters")
        String clientPhoneNumber

) {


}
