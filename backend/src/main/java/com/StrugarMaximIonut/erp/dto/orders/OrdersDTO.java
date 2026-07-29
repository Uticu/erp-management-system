package com.StrugarMaximIonut.erp.dto.orders;

import com.StrugarMaximIonut.erp.dto.orderDetails.OrderDetailsDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record OrdersDTO (
        @NotNull(message = "Order ID is mandatory")
        Integer orderId,

        @NotBlank(message = "Name is mandatory")
        String clientName,

        @NotNull(message = "Date is mandatory")
        LocalDateTime orderDate,

        @NotBlank(message = "Status is mandatory")
        @Size(max = 255, message = "Status cannot exceed 255 characters")
        String orderStatus,

        @NotBlank(message = "Delivery address is mandatory")
        @Size(max = 255, message = "Delivery address cannot exceed 255 characters")
        String orderDeliveryAddress,

        @NotBlank(message = "Client phone number is mandatory")
        @Size(max = 20, message = "Client phone number cannot exceed 20 characters")
        String clientPhoneNumber,

        @NotNull(message = "Cart is empty")
        List<OrderDetailsDTO> orderDetailsDTOList
) {


}
