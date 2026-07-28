package com.StrugarMaximIonut.erp.dto.orders;

import com.StrugarMaximIonut.erp.model.Orders;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrdersDTOMapper implements Function<Orders, OrdersDTO> {
    @Override
    public OrdersDTO apply(Orders orders) {
        return new OrdersDTO(
                orders.getOrderID(),
                orders.getClient().getClientID(),
                orders.getOrderDate(),
                orders.getOrderStatus(),
                orders.getOrderDeliveryAddress(),
                orders.getClient().getClientPhoneNumber());
    }

}
