package com.StrugarMaximIonut.erp.dto.products;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.math.BigDecimal;

public record ProductsDTO(
    @NotNull(message = "ID is mandatory")
    Integer productID,

    @NotBlank(message = "Name is mandatory")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    String productName,

    @NotNull(message = "Price is mandatory")
    @Digits(integer = 8, fraction = 2, message = "Price format is invalid")
    BigDecimal productPrice,

    @NotNull(message = "Stock is mandatory")
    Integer productStock
){
}
