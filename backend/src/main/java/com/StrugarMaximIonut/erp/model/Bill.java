package com.StrugarMaximIonut.erp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billID;

    @Column(name = "billIssueDate", nullable = false)
    private LocalDateTime billIssueDate;

    @Column(name = "billSeries", length = 3, nullable = false, unique = true)
    private String billSeries;

    @Column(name = "billNumber", length = 5, nullable = false, unique = true)
    private String billNumber;

    @Column(name = "billTotalAmount", precision = 10, scale = 2, nullable = false)
    private BigDecimal billTotalAmount;

    @Column(name = "clientName", nullable = false, length = 255)
    private String clientName;

    @Column(name = "clientAddress", nullable = false, length = 255)
    private String clientAddress;

    @OneToOne
    @JoinColumn(name = "orderID")
    private Orders orders;

    public Bill(){}

    public Bill(Integer billID, Orders orders, BigDecimal billTotalAmount, String billNumber, String billSeries, LocalDateTime billIssueDate) {
        this.billID = billID;
        this.orders = orders;
        this.billTotalAmount = billTotalAmount;
        this.billNumber = billNumber;
        this.billSeries = billSeries;
        this.billIssueDate = billIssueDate;
    }

    public Integer getBillID() {
        return billID;
    }

    public void setClientName(String clientName){
        this.clientName = clientName;
    }

    public void setClientAddress(String clientAddress){
        this.clientAddress = clientAddress;
    }

    public String getClientName(){
        return this.clientName;
    }

    public String getClientAddress(){
        return this.clientAddress;
    }

    public LocalDateTime getBillIssueDate() {
        return billIssueDate;
    }

    public void setBillIssueDate(LocalDateTime billIssueDate) {
        this.billIssueDate = billIssueDate;
    }

    public String getBillSeries() {
        return billSeries;
    }

    public void setBillSeries(String billSeries) {
        this.billSeries = billSeries;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public BigDecimal getBillTotalAmount() {
        return billTotalAmount;
    }

    public void setBillTotalAmount(BigDecimal billTotalAmount) {
        this.billTotalAmount = billTotalAmount;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Bill bill)) return false;
        return Objects.equals(billID, bill.billID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(billID);
    }
}
