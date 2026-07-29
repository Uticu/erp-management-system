package com.StrugarMaximIonut.erp.repository;

import com.StrugarMaximIonut.erp.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByClientEmail(String email);

    boolean existsByClientPhoneNumber(String phoneNumber);

    Client findByClientEmail(String email);

    List<Client> findByClientName(String name);

    List<Client> findClientsByClientNameStartsWith(String clientName);

    List<Client> findClientsByClientNameEndsWith(String clientName);

    List<Client> findByClientNameContains(String clientName);

    Client findByClientPhoneNumber(String clientPhoneNumber);
}
