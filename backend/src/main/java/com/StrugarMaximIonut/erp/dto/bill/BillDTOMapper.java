package com.StrugarMaximIonut.erp.dto.bill;

import com.StrugarMaximIonut.erp.model.Bill;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BillDTOMapper implements Function<Bill, BillDTO> {
    @Override
    public BillDTO apply(Bill bill){
        return new BillDTO(
                bill.getOrders().getOrderID(),
                bill.getBillIssueDate(),
                bill.getBillSeries(),
                bill.getBillNumber(),
                bill.getBillTotalAmount(),
                bill.getClientName(),
                bill.getClientAddress());
    }
}
