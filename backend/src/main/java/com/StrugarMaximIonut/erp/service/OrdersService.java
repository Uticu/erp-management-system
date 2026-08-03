package com.StrugarMaximIonut.erp.service;

import com.StrugarMaximIonut.erp.dto.client.ClientDTO;
import com.StrugarMaximIonut.erp.dto.orderDetails.OrderDetailsDTOMapper;
import com.StrugarMaximIonut.erp.dto.orderDetails.OrderDetailsRequestDTO;
import com.StrugarMaximIonut.erp.dto.orders.OrdersDTO;
import com.StrugarMaximIonut.erp.dto.orders.OrdersDTOMapper;
import com.StrugarMaximIonut.erp.dto.orders.OrdersRequestDTO;
import com.StrugarMaximIonut.erp.dto.orders.OrdersRequestMapper;
import com.StrugarMaximIonut.erp.dto.products.ProductsDTO;
import com.StrugarMaximIonut.erp.dto.products.ProductsDTOMapper;
import com.StrugarMaximIonut.erp.dto.products.ProductsRequestMapper;
import com.StrugarMaximIonut.erp.exception.client.ClientNotFoundException;
import com.StrugarMaximIonut.erp.exception.orders.NoOrdersException;
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

import org.springframework.stereotype.Service;

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

    public OrdersService(OrdersRepository ordersRepository, ClientRepository clientRepository,
                         OrdersDTOMapper ordersDTOMapper, OrdersRequestMapper ordersRequestMapper,
                         ProductsRepository productsRepository) {
        this.ordersRepository = ordersRepository;
        this.clientRepository = clientRepository;
        this.ordersDTOMapper = ordersDTOMapper;
        this.ordersRequestMapper = ordersRequestMapper;
        this.productsRepository = productsRepository;
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
                throw new InsuficientStock("Product stock is less than the client quantity request");
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
        if(!ordersRepository.existsById(id)){
            throw new NoOrdersException("Order is not in the database");
        }
        ordersRepository.deleteById(id);
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
                throw new InsuficientStock("Product stock is less than the client quantity request");
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

}
