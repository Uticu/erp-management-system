package com.StrugarMaximIonut.erp.dto.orders;

import com.StrugarMaximIonut.erp.dto.orderDetails.OrderDetailsRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record OrdersRequestDTO (
        @NotNull(message = "Client id is mandatory")
        Integer clientID,

        @NotBlank(message = "Order date is mandatory")
        LocalDateTime orderDate,

        @NotBlank(message = "Status is mandatory")
        @Size(max = 255, message = "Status cannot exceed 255 characters")
        String orderStatus,

        @NotBlank(message = "Delivery address is mandatory")
        @Size(max = 255, message = "Delivery address cannot exceed 255 characters")
        String orderDeliveryAddress,

        @NotNull(message = "Cart is empty")
        List<OrderDetailsRequestDTO> cartItems
) {

}
