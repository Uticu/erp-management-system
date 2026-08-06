package com.StrugarMaximIonut.erp.service;

import com.StrugarMaximIonut.erp.dto.client.ClientDTO;
import com.StrugarMaximIonut.erp.dto.orderDetails.OrderDetailsDTOMapper;
import com.StrugarMaximIonut.erp.dto.orderDetails.OrderDetailsRequestDTO;
import com.StrugarMaximIonut.erp.dto.orders.*;
import com.StrugarMaximIonut.erp.dto.products.ProductsDTO;
import com.StrugarMaximIonut.erp.dto.products.ProductsDTOMapper;
import com.StrugarMaximIonut.erp.dto.products.ProductsRequestMapper;
import com.StrugarMaximIonut.erp.enums.OrderStatus;
import com.StrugarMaximIonut.erp.exception.client.ClientNotFoundException;
import com.StrugarMaximIonut.erp.exception.orders.NoOrdersException;
import com.StrugarMaximIonut.erp.exception.orders.OrderCancelledException;
import com.StrugarMaximIonut.erp.exception.orders.OrderNotFoundException;
import com.StrugarMaximIonut.erp.exception.orders.InsuficientStock;
import com.StrugarMaximIonut.erp.exception.products.NoProductsException;
import com.StrugarMaximIonut.erp.exception.products.ProductFoundException;
import com.StrugarMaximIonut.erp.exception.products.ProductNotFoundException;
import com.StrugarMaximIonut.erp.model.Client;
import com.StrugarMaximIonut.erp.model.OrderDetails;
import com.StrugarMaximIonut.erp.model.Orders;
import com.StrugarMaximIonut.erp.model.Products;
import com.StrugarMaximIonut.erp.repository.ClientRepository;
import com.StrugarMaximIonut.erp.repository.OrderDetailsRepository;
import com.StrugarMaximIonut.erp.repository.OrdersRepository;
import com.StrugarMaximIonut.erp.repository.ProductsRepository;
import jakarta.transaction.Transactional;

import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ClientRepository clientRepository;
    private final OrdersDTOMapper ordersDTOMapper;
    private final OrdersRequestMapper ordersRequestMapper;
    private final ProductsRepository productsRepository;
    private final ProductsDTOMapper productsDTOMapper;

    public OrdersService(OrdersRepository ordersRepository, ClientRepository clientRepository,
                         OrdersDTOMapper ordersDTOMapper, OrdersRequestMapper ordersRequestMapper,
                         ProductsRepository productsRepository, ProductsDTOMapper productsDTOMapper) {
        this.ordersRepository = ordersRepository;
        this.clientRepository = clientRepository;
        this.ordersDTOMapper = ordersDTOMapper;
        this.ordersRequestMapper = ordersRequestMapper;
        this.productsRepository = productsRepository;
        this.productsDTOMapper = productsDTOMapper;
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

    public List<OrdersDTO> getOrdersByClientId(Integer id){
        List<Orders> ordersList = ordersRepository.findAllByClient_ClientID(id);

        if(ordersList.isEmpty()){
            throw new NoOrdersException("This client has no orders");
        }

        return ordersList.stream()
                .map(ordersDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> getProductsByClientId(Integer id){
        List<Orders> ordersList = ordersRepository.findAllByClient_ClientID(id);

        if(ordersList.isEmpty()){
            throw new NoOrdersException("This client has no orders");
        }

        List<Products> productsList = ordersList.stream()
                .flatMap(orders -> orders.getOrderDetails().stream())
                .map(OrderDetails::getProducts)
                .distinct().collect(Collectors.toList());

        return productsList.stream()
                .map(productsDTOMapper)
                .collect(Collectors.toList());
    }

    public List<OrdersDTO> getOrdersByStatus(OrderStatus orderStatus){
        List<Orders> list = ordersRepository.findAllByOrderStatusIs(orderStatus);

        if(list.isEmpty()){
            throw new NoProductsException("There are no orders with this status");
        }

        return list.stream()
                .map(ordersDTOMapper)
                .collect(Collectors.toList());
    }

    public List<OrdersDTO> getOrdersBetweenDates(LocalDateTime minDate, LocalDateTime maxDate){
        List<Orders> ordersList = ordersRepository.findAllByOrderDateBetween(minDate, maxDate);

        if(ordersList.isEmpty()){
            throw new NoOrdersException("There are no orders between this dates");
        }

        return ordersList.stream()
                .map(ordersDTOMapper)
                .collect(Collectors.toList());
    }

    public List<OrdersDTO> getOrdersByDeliveryAddress(String address){
        List<Orders> list = ordersRepository.findAllByOrderDeliveryAddress(address);

        if(list.isEmpty()){
            throw new NoOrdersException("There are no orders from this address");
        }

        return list.stream()
                .map(ordersDTOMapper)
                .collect(Collectors.toList());
    }

    public OrdersDTO insertOrder(OrdersRequestDTO ordersRequestDTO){
        Client client = clientRepository.findById(ordersRequestDTO.clientID())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        Orders orders = ordersRequestMapper.apply(ordersRequestDTO, client);
        ordersRepository.save(orders);

        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for(OrderDetailsRequestDTO index :  ordersRequestDTO.cartItems()) {
            Products products = productsRepository.findById(index.productID())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            if (products.getProductStock() < index.quantity()) {
                throw new InsuficientStock("Product stock for " + products.getProductName() + " is less than the client quantity request");
            }
            products.setProductStock(products.getProductStock() - index.quantity());

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setProducts(products);
            orderDetails.setOrders(orders);
            orderDetails.setOrderDetailsQuantity(index.quantity());
            orderDetails.setSellingPriceAtTheMoment(products.getProductPrice());

            orderDetailsList.add(orderDetails);
        }

        orders.getOrderDetails().addAll(orderDetailsList);
        ordersRepository.save(orders);

        return ordersDTOMapper.apply(orders);
    }

    public void deleteOrderById(Integer id){
        Orders order = findOrderEntityByID(id);

        for(OrderDetails index : order.getOrderDetails()){
            Products product = index.getProducts();
            product.setProductStock(product.getProductStock() + index.getOrderDetailsQuantity());
        }

        ordersRepository.deleteById(id);
    }

    public OrdersDTO cancelOrderById(Integer id){
        Orders order = findOrderEntityByID(id);

        if(order.getOrderStatus().equals(OrderStatus.CANCELLED)){
            throw new OrderCancelledException("Order already cancelled");
        }

        order.setOrderStatus(OrderStatus.CANCELLED);

        for(OrderDetails index : order.getOrderDetails()){
            Products product = index.getProducts();
            product.setProductStock(product.getProductStock() + index.getOrderDetailsQuantity());
        }

        ordersRepository.save(order);
        return ordersDTOMapper.apply(order);
    }

    public OrdersDTO modifyOrderById(OrdersRequestDTO ordersRequestDTO, Integer id){
        Orders order = findOrderEntityByID(id);

        for(OrderDetails index : order.getOrderDetails()){
            index.getProducts().setProductStock(index.getProducts().getProductStock() + index.getOrderDetailsQuantity());
        }

        order.getOrderDetails().clear();

        Client client = clientRepository.findById(ordersRequestDTO.clientID())
                .orElseThrow(()-> new ClientNotFoundException("Client not found"));
        order.setClient(client);
        order.setOrderDeliveryAddress(ordersRequestDTO.orderDeliveryAddress());

        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for(OrderDetailsRequestDTO index : ordersRequestDTO.cartItems()){
            Products products = productsRepository.findById(index.productID())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            if (products.getProductStock() < index.quantity()) {
                throw new InsuficientStock("Product stock for " + products.getProductName() + " is less than the client quantity request");
            }
            products.setProductStock(products.getProductStock() - index.quantity());

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setProducts(products);
            orderDetails.setOrders(order);
            orderDetails.setOrderDetailsQuantity(index.quantity());
            orderDetails.setSellingPriceAtTheMoment(products.getProductPrice());

            orderDetailsList.add(orderDetails);
        }

        order.getOrderDetails().addAll(orderDetailsList);
        ordersRepository.save(order);

        return ordersDTOMapper.apply(order);
    }

    public OrdersDTO modifyOrderStatus(OrderUpdateStatusDTO statusDTO, Integer id){
        Orders order = findOrderEntityByID(id);

        if(order.getOrderStatus().equals(OrderStatus.CANCELLED)){
            throw new OrderCancelledException("Order is cancelled");
        }

        order.setOrderStatus(statusDTO.status());

        ordersRepository.save(order);
        return ordersDTOMapper.apply(order);
    }

}
