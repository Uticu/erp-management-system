package com.StrugarMaximIonut.erp.dto.orderDetails;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderDetailsRequestDTO(
        @NotNull(message = "Product id is mandatory")
        Integer productID,

        @NotNull(message = "Product quantity is mandatory")
        @Min(value = 1, message = "Product quantity must be at least 1")
        Integer quantity
){
}
