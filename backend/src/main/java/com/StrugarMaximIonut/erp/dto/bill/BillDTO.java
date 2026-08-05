package com.StrugarMaximIonut.erp.dto.bill;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BillDTO(
        @NotNull(message = "Order ID is mandatory")
        Integer orderID,

        @NotNull(message = "Date is mandatory")
        LocalDateTime billIssueDate,

        @NotBlank(message = "Bill series is mandatory")
        @Size(min = 3, max = 3, message = "Bill series must be 3 characters")
        String billSeries,

        @NotNull(message = "Bill number is mandatory")
        Integer billNumber,

        @NotNull(message = "Total amount of the bill is mandatory")
        @Digits(integer = 8, fraction = 2, message = "Total amount format is invalid")
        BigDecimal billTotalAmount,

        @NotBlank(message = "Client name is mandatory")
        @Size(min = 1, max = 255, message = "Client name must have between 1 and 255 characters")
        String clientName,

        @NotBlank(message = "Client address is mandatory")
        @Size(min = 1, max = 255, message = "Client address must have between 1 and 255 characters")
        String clientAddress
) {
}
