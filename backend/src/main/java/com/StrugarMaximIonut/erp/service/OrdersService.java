package com.StrugarMaximIonut.erp.service;

import com.StrugarMaximIonut.erp.dto.orders.OrdersDTO;
import com.StrugarMaximIonut.erp.dto.orders.OrdersDTOMapper;
import com.StrugarMaximIonut.erp.dto.orders.OrdersRequestMapper;
import com.StrugarMaximIonut.erp.exception.orders.NoOrdersException;
import com.StrugarMaximIonut.erp.exception.orders.OrderNotFoundException;
import com.StrugarMaximIonut.erp.model.Orders;
import com.StrugarMaximIonut.erp.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ClientService clientService;
    private final OrdersDTOMapper ordersDTOMapper;
    private final OrdersRequestMapper ordersRequestMapper;

    public OrdersService(OrdersRepository ordersRepository, ClientService clientService, OrdersDTOMapper ordersDTOMapper, OrdersRequestMapper ordersRequestMapper){
        this.ordersRepository = ordersRepository;
        this.clientService = clientService;
        this.ordersDTOMapper = ordersDTOMapper;
        this.ordersRequestMapper = ordersRequestMapper;
    }

    public List<OrdersDTO> getAllOrders() {
        List<Orders> orders = ordersRepository.findAll();
        if(orders.isEmpty()){
            throw new NoOrdersException("The database has no orders in it");
        }

        return orders.stream()
                .map(ordersDTOMapper)
                .collect(Collectors.toList());
    }

    private Orders findOrderEntityByID(Integer id){
        return ordersRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " is not in the database"));
    }

    public OrdersDTO getOrderByID(Integer id) {
        Orders orders =  findOrderEntityByID(id);
        return ordersDTOMapper.apply(orders);
    }


}
