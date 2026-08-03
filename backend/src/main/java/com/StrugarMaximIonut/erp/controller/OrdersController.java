package com.StrugarMaximIonut.erp.controller;

import com.StrugarMaximIonut.erp.dto.orders.OrdersDTO;
import com.StrugarMaximIonut.erp.dto.orders.OrdersRequestDTO;
import com.StrugarMaximIonut.erp.dto.orders.OrdersRequestMapper;
import com.StrugarMaximIonut.erp.service.OrdersService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService){
        this.ordersService = ordersService;
    }

    @GetMapping()
    public ResponseEntity<List<OrdersDTO>> getOrders(){
        List<OrdersDTO> list = ordersService.getAllOrders();
        return  ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderByID(@PathVariable Integer id){
        OrdersDTO ordersDTO = ordersService.getOrderByID(id);
        return ResponseEntity.ok(ordersDTO);
    }

    @PostMapping("")
    public ResponseEntity<OrdersDTO> insertOrder(@RequestBody OrdersRequestDTO ordersRequestDTO){
        OrdersDTO ordersDTO = ordersService.insertOrder(ordersRequestDTO);
        return ResponseEntity.ok(ordersDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id){
        ordersService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersDTO> modifyOrder(@RequestBody OrdersRequestDTO ordersRequestDTO, @PathVariable Integer id){
        OrdersDTO modifiedOrder = ordersService.modifyOrderById(ordersRequestDTO, id);
        return ResponseEntity.ok(modifiedOrder);
    }

}
