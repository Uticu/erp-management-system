package com.StrugarMaximIonut.erp.dto.orderDetails;

import jakarta.validation.constraints.*;


import java.math.BigDecimal;

public record OrderDetailsDTO (
    @NotBlank(message = "Product name is mandatory")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    String productName,

    @NotNull(message = "Product quantity is mandatory")
    @Min( value = 1, message = "Product stock must be at least 1")
    Integer quantity,

    @NotNull(message = "Product price is mandatory")
    @Digits(integer = 8, fraction = 2, message = "Price format is invalid")
    BigDecimal sellingPrice
){

}
