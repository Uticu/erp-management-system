package com.StrugarMaximIonut.erp.service;

import com.StrugarMaximIonut.erp.dto.orders.OrdersDTOMapper;
import com.StrugarMaximIonut.erp.dto.orders.OrdersRequestMapper;
import com.StrugarMaximIonut.erp.repository.OrdersRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final OrdersDTOMapper ordersDTOMapper;
    private final OrdersRequestMapper ordersRequestMapper;

    public OrdersService(OrdersRepository ordersRepository, OrdersDTOMapper ordersDTOMapper, OrdersRequestMapper ordersRequestMapper){
        this.ordersRepository = ordersRepository;
        this.ordersDTOMapper = ordersDTOMapper;
        this.ordersRequestMapper = ordersRequestMapper;
    }
}
