package com.StrugarMaximIonut.erp.controller;


import com.StrugarMaximIonut.erp.dto.bill.BillDTO;
import com.StrugarMaximIonut.erp.dto.bill.BillRequestDTO;
import com.StrugarMaximIonut.erp.service.BillService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
@Validated
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
    public ResponseEntity<BillDTO> getBillById(
            @Min(value = 1, message = "Id must be atleast 1")
            @PathVariable Integer id){
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
    public ResponseEntity<BillDTO> getBillBySeriesAndNumber(
            @NotBlank(message = "Bill series is mandatory")
            @Size(min = 3, max = 3, message = "Series must be 3 characters")
            @RequestParam String series,
            @Min(value = 1, message = "Id must be atleast 1")
            @RequestParam Integer number){
        BillDTO bill = billService.getBillBySeriesAndNumber(series, number);
        return ResponseEntity.ok(bill);
    }

    @PostMapping()
    public ResponseEntity<BillDTO> insertBill(@Valid @RequestBody BillRequestDTO billRequestDTO){
        BillDTO bill = billService.insertBill(billRequestDTO);
        return ResponseEntity.ok(bill);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBillById(@Min(value = 1, message = "Id must be atleast 1")
                                                   @PathVariable Integer id){
        billService.deleteBillById(id);
        return ResponseEntity.noContent().build();
    }
}
