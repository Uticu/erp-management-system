package com.StrugarMaximIonut.erp.controller;


import com.StrugarMaximIonut.erp.dto.bill.BillDTO;
import com.StrugarMaximIonut.erp.dto.bill.BillRequestDTO;
import com.StrugarMaximIonut.erp.service.BillService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService){
        this.billService = billService;
    }

    @GetMapping("")
    public ResponseEntity<List<BillDTO>> getAllBills(){
        List<BillDTO> billDTOList = billService.getAllBills();
        return ResponseEntity.ok(billDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDTO> getBillById(@PathVariable Integer id){
        BillDTO bill = billService.getBillById(id);
        return ResponseEntity.ok(bill);
    }

    @GetMapping(value = "/date", params = {"minDate", "maxDate"})
    public ResponseEntity<List<BillDTO>> geBillsBetweenDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate minDate,
                                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate maxDate){
        LocalDateTime start = minDate.atStartOfDay();
        LocalDateTime end =maxDate.atTime(LocalTime.MAX);

        List<BillDTO> list = billService.getAllBillsBetweenDates(start, end);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/seriesAndNumber", params = {"series", "number"})
    public ResponseEntity<BillDTO> getBillBySeriesAndNumber(@RequestParam String series, @RequestParam Integer number){
        BillDTO bill = billService.getBillBySeriesAndNumber(series, number);
        return ResponseEntity.ok(bill);
    }

    @PostMapping()
    public ResponseEntity<BillDTO> insertBill(@RequestBody BillRequestDTO billRequestDTO){
        BillDTO bill = billService.insertBill(billRequestDTO);
        return ResponseEntity.ok(bill);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBillById(@PathVariable Integer id){
        billService.deleteBillById(id);
        return ResponseEntity.noContent().build();
    }
}
