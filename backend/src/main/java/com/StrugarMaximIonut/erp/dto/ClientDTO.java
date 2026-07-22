package com.StrugarMaximIonut.erp.dto;

import jakarta.validation.constraints.*;

public record ClientDTO (
        @NotNull(message = "ID is mandatory")
        Integer clientID,

        @NotBlank(message = "Name is mandatory")
        @Size(max = 255, message = "Name cannot exceed 255 characters")
        String clientName,

        @Email(message = "Email must be valid")
        @NotBlank(message = "Email is mandatory")
        @Size(max = 255, message = "Email cannot exceed 255 characters")
        String clientEmail,

        @NotBlank(message = "Address must be valid")
        @Size(max = 255, message = "Address cannot exceed 255 characters")
        String clientAddress,

        @Size(min = 1, max = 20, message = "Phone number must contain between 1 and 20 characters")
        @NotBlank(message = "Phone number is mandatory")
        String clientPhoneNumber
) {
}
