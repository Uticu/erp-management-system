package com.StrugarMaximIonut.erp.model;

import com.StrugarMaximIonut.erp.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    @Column(name = "orderDate", nullable = false, unique = false)
    private LocalDateTime orderDate;

    @Column(name = "orderStatus", length = 255, nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "orderDeliveryAddress", length = 255, nullable = false, unique = false)
    private String orderDeliveryAddress;

    @Column(name = "clientPhoneNumber", length = 20, nullable = false)
    private String clientPhoneNumber;

    @ManyToOne
    @JoinColumn(name = "clientID")
    private Client client;

    @OneToMany(mappedBy = "orders", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Orders(){

    }

    public Orders(Integer orderID, LocalDateTime orderDate, OrderStatus orderStatus, String orderDeliveryAddress,
                  String clientPhoneNumber, Client client) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderDeliveryAddress = orderDeliveryAddress;
        this.clientPhoneNumber = clientPhoneNumber;
        this.client = client;
    }

    public List<OrderDetails> getOrderDetails(){
        return this.orderDetails;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getOrderDeliveryAddress() {
        return orderDeliveryAddress;
    }

    public void setOrderDeliveryAddress(String orderDeliveryAddress) {
        this.orderDeliveryAddress = orderDeliveryAddress;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setClientPhoneNumber(String clientPhoneNumber){
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientPhoneNumber(){
        return this.clientPhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Orders orders)) return false;
        return Objects.equals(orderID, orders.orderID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderID);
    }
}
