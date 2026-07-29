package com.StrugarMaximIonut.erp.controller;


import com.StrugarMaximIonut.erp.service.BillService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService){
        this.billService = billService;
    }
}
