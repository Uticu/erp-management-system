package com.StrugarMaximIonut.erp.model;

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
    private String orderStatus;

    @Column(name = "orderDeliveryAddress", length = 255, nullable = false, unique = false)
    private String orderDeliveryAddress;

    @ManyToOne
    @JoinColumn(name = "clientID")
    private Client client;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Orders(){

    }

    public Orders(Integer orderID, LocalDateTime orderDate, String orderStatus, String orderDeliveryAddress, Client client) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderDeliveryAddress = orderDeliveryAddress;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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
