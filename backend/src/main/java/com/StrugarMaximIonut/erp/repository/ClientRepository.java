package com.StrugarMaximIonut.erp.repository;

import com.StrugarMaximIonut.erp.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByClientEmail(String email);

    boolean existsByClientPhoneNumber(String phoneNumber);
}
