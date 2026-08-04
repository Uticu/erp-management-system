package com.StrugarMaximIonut.erp.controller;

import com.StrugarMaximIonut.erp.dto.orderDetails.OrderDetailsDTO;
import com.StrugarMaximIonut.erp.dto.orders.OrderUpdateStatusDTO;
import com.StrugarMaximIonut.erp.dto.orders.OrdersDTO;
import com.StrugarMaximIonut.erp.dto.orders.OrdersRequestDTO;
import com.StrugarMaximIonut.erp.dto.products.ProductsDTO;
import com.StrugarMaximIonut.erp.enums.OrderStatus;
import com.StrugarMaximIonut.erp.service.OrdersService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @GetMapping("/client/{id}")
    public ResponseEntity<List<OrdersDTO>> getOrdersByClientId(@PathVariable Integer id){
        List<OrdersDTO> list = ordersService.getOrdersByClientId(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/client/products/{id}")
    public ResponseEntity<List<ProductsDTO>> getProductsByClientId(@PathVariable Integer id){
        List<ProductsDTO> list = ordersService.getProductsByClientId(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/date", params = {"minDate", "maxDate"})
    public ResponseEntity<List<OrdersDTO>> getOrdersBetweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate minDate,
                                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate maxDate){

        LocalDateTime startOfDay = minDate.atStartOfDay();
        LocalDateTime endOfDay = maxDate.atTime(LocalTime.MAX);

        List<OrdersDTO> list = ordersService.getOrdersBetweenDates(startOfDay, endOfDay);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/status", params = "orderStatus")
    public ResponseEntity<List<OrdersDTO>> getOrdersByStatus(OrderStatus orderStatus){
        List<OrdersDTO> list = ordersService.getOrdersByStatus(orderStatus);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/deliveryAddress", params = "address")
    public ResponseEntity<List<OrdersDTO>> getOrdersByDeliveryAddress(String address){
        List<OrdersDTO> list = ordersService.getOrdersByDeliveryAddress(address);
        return ResponseEntity.ok(list);
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

    @PutMapping("/cancel/{id}")
    public ResponseEntity<OrdersDTO> cancelOrder(@PathVariable Integer id){
        OrdersDTO cancelledOrder = ordersService.cancelOrderById(id);
        return ResponseEntity.ok(cancelledOrder);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<OrdersDTO> modifyOrder(@RequestBody OrdersRequestDTO ordersRequestDTO, @PathVariable Integer id){
        OrdersDTO modifiedOrder = ordersService.modifyOrderById(ordersRequestDTO, id);
        return ResponseEntity.ok(modifiedOrder);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<OrdersDTO> modifyOrderStatus(@RequestBody OrderUpdateStatusDTO statusDTO, @PathVariable Integer id){
        OrdersDTO modifiedOrder = ordersService.modifyOrderStatus(statusDTO, id);
        return ResponseEntity.ok(modifiedOrder);
    }

}
