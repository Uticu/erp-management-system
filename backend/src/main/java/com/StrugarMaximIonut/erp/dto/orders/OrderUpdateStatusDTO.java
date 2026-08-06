package com.StrugarMaximIonut.erp.dto.orders;

import com.StrugarMaximIonut.erp.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record OrderUpdateStatusDTO(
        @NotNull(message = "Status is mandatory")
        OrderStatus status
) {
}
