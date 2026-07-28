package com.StrugarMaximIonut.erp.dto.orderDetails;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record OrderDetailsDTO (
    @NotBlank(message = "Product name is mandatory")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    String productName,

    @NotNull(message = "Product quantity is mandatory")
    Integer quantity,

    @NotNull(message = "Product price is mandatory")
    BigDecimal sellingPrice
){

}
