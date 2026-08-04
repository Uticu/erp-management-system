package com.StrugarMaximIonut.erp.dto.orders;

import com.StrugarMaximIonut.erp.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderUpdateStatusDTO(
        @NotNull(message = "Status is mandatory")
        OrderStatus status
) {
}
