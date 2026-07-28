package com.StrugarMaximIonut.erp.dto.orderDetails;

import com.StrugarMaximIonut.erp.model.OrderDetails;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrderDetailsDTOMapper implements Function<OrderDetails, OrderDetailsDTO> {
    @Override
    public OrderDetailsDTO apply(OrderDetails orderDetails) {
        return new OrderDetailsDTO(
                orderDetails.getProducts().getProductName(),
                orderDetails.getOrderDetailsQuantity(),
                orderDetails.getSellingPriceAtTheMoment()
        );
    }
}
