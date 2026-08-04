package com.StrugarMaximIonut.erp.repository;

import com.StrugarMaximIonut.erp.enums.OrderStatus;
import com.StrugarMaximIonut.erp.model.Client;
import com.StrugarMaximIonut.erp.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findAllByClient_ClientID(Integer clientClientID);

    List<Orders> findAllByOrderDateBetween(LocalDateTime orderDateAfter, LocalDateTime orderDateBefore);

    List<Orders> findAllByOrderStatusIs(OrderStatus orderStatus);

    List<Orders> findAllByOrderDeliveryAddress(String orderDeliveryAddress);
}
