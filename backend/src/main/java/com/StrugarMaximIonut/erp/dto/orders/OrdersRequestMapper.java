package com.StrugarMaximIonut.erp.dto.orders;

import com.StrugarMaximIonut.erp.enums.OrderStatus;
import com.StrugarMaximIonut.erp.model.Client;
import com.StrugarMaximIonut.erp.model.Orders;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.BiFunction;


@Service
public class OrdersRequestMapper implements BiFunction<OrdersRequestDTO, Client, Orders> {
    @Override
    public Orders apply(OrdersRequestDTO ordersRequestDTO, Client client){
        Orders orders = new Orders();
        orders.setOrderDate(LocalDateTime.now());
        orders.setOrderDeliveryAddress(ordersRequestDTO.orderDeliveryAddress());
        orders.setOrderStatus(OrderStatus.PENDING);
        orders.setClient(client);
        return orders;
    }
}
