package com.StrugarMaximIonut.erp.dto;

import jakarta.validation.constraints.*;

public record ClientDTO (
        @NotNull(message = "ID is mandatory")
        Integer clientID,

        @NotBlank(message = "Name is mandatory")
        String clientName,

        @Email(message = "Email must be valid")
        @NotBlank(message = "Email is mandatory")
        String clientEmail,

        @NotBlank(message = "Address must be valid")
        String clientAddress,

        @Size(min = 1, max = 20, message = "Phone number must contain between 1 and 20 characters")
        @NotBlank(message = "Phone number is mandatory")
        String clientPhoneNumber
) {
}
