package com.StrugarMaximIonut.erp.service;

import com.StrugarMaximIonut.erp.repository.BillRepository;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final OrdersService ordersService;

    public BillService(BillRepository billRepository, OrdersService ordersService){
        this.billRepository = billRepository;
        this.ordersService = ordersService;
    }


}
