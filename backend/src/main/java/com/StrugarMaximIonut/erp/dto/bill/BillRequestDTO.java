package com.StrugarMaximIonut.erp.dto.bill;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BillRequestDTO (
        @NotNull(message = "Order id is mandatory")
        Integer orderID,
        @NotBlank(message = "Bill series is mandatory")
        @Size(min = 3, max = 3, message = "Series must be 3 characters")
        String billSeries
){
}
