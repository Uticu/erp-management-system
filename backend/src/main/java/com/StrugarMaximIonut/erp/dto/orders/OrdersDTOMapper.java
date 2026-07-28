package com.StrugarMaximIonut.erp.dto.orders;

import com.StrugarMaximIonut.erp.dto.orderDetails.OrderDetailsDTOMapper;
import com.StrugarMaximIonut.erp.model.Orders;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrdersDTOMapper implements Function<Orders, OrdersDTO> {
    private final OrderDetailsDTOMapper orderDetailsDTOMapper;

    public OrdersDTOMapper(OrderDetailsDTOMapper orderDetailsDTOMapper){
        this.orderDetailsDTOMapper = orderDetailsDTOMapper;
    }

    @Override
    public OrdersDTO apply(Orders orders) {
        return new OrdersDTO(
                orders.getOrderID(),
                orders.getClient().getClientID(),
                orders.getOrderDate(),
                orders.getOrderStatus(),
                orders.getOrderDeliveryAddress(),
                orders.getClient().getClientPhoneNumber(),
                orders.getOrderDetails().stream().map(orderDetailsDTOMapper).collect(Collectors.toList()));
    }

}
