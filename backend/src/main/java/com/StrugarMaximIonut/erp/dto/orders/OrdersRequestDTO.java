package com.StrugarMaximIonut.erp.dto.orders;

import com.StrugarMaximIonut.erp.dto.orderDetails.OrderDetailsRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.util.List;

public record OrdersRequestDTO (
        @NotNull(message = "Client id is mandatory")
        Integer clientID,

        @NotBlank(message = "Delivery address is mandatory")
        @Size(max = 255, message = "Delivery address cannot exceed 255 characters")
        String orderDeliveryAddress,

        @NotEmpty(message = "Cart is empty")
        @Valid
        List<OrderDetailsRequestDTO> cartItems
) {

}
