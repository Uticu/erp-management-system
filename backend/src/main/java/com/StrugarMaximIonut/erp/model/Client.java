package com.StrugarMaximIonut.erp.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientID;

    @Column(name = "clientName", length = 255, nullable = false, unique = false)
    private String clientName;

    @Column(name = "clientEmail", length = 255, nullable = false, unique = true)
    private String clientEmail;

    @Column(name = "clientAddress", length = 255, nullable = false, unique = false)
    private String clientAddress;

    @Column(name = "clientPhoneNumber", length = 20, nullable = false, unique = true)
    private String clientPhoneNumber;

    public Client(){
    }

    public Client(Integer clientID, String clientName, String clientEmail, String clientAddress) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientAddress = clientAddress;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Client client)) return false;
        return Objects.equals(clientID, client.clientID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(clientID);
    }
}
