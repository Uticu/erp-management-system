package com.StrugarMaximIonut.erp.model;

import com.StrugarMaximIonut.erp.service.ProductsService;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "OrderDetails")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailsID;

    @ManyToOne
    @JoinColumn(name = "orderID")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Products products;

    @Column(name = "orderDetailsQuantity", nullable = false, unique = false)
    private Integer orderDetailsQuantity;

    @Column(name = "sellingPriceAtTheMoment", nullable = false, precision = 10, scale = 2, unique = false)
    private BigDecimal sellingPriceAtTheMoment;


    public OrderDetails(){}

    public OrderDetails(Integer orderDetailsID, Orders orders, Products products, Integer orderDetailsQuantity, BigDecimal sellingPriceAtTheMoment) {
        this.orderDetailsID = orderDetailsID;
        this.orders = orders;
        this.products = products;
        this.orderDetailsQuantity = orderDetailsQuantity;
        this.sellingPriceAtTheMoment = sellingPriceAtTheMoment;
    }

    public Integer getOrderDetailsID() {
        return orderDetailsID;
    }

    public BigDecimal getSellingPriceAtTheMoment() {
        return sellingPriceAtTheMoment;
    }

    public void setSellingPriceAtTheMoment(BigDecimal sellingPriceAtTheMoment) {
        this.sellingPriceAtTheMoment = sellingPriceAtTheMoment;
    }

    public Integer getOrderDetailsQuantity() {
        return orderDetailsQuantity;
    }

    public void setOrderDetailsQuantity(Integer orderDetailsQuantity) {
        this.orderDetailsQuantity = orderDetailsQuantity;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderDetails that)) return false;
        return Objects.equals(orderDetailsID, that.orderDetailsID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderDetailsID);
    }
}
