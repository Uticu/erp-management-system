package com.StrugarMaximIonut.erp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productID;

    @Column(name = "productName", length = 255, nullable = false, unique = true)
    private String productName;

    @Column(name = "productPrice", nullable = false, precision = 10, scale = 2, unique = false)
    private BigDecimal productPrice;

    @Column(name = "productStock", nullable = false)
    private Integer productStock;

    @OneToMany(mappedBy = "products")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Products(){
    }

    public Products(Integer productID, String productName, BigDecimal productPrice, Integer productStock) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public Integer getProductID() {
        return productID;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Products products)) return false;
        return Objects.equals(productID, products.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productID);
    }
}
