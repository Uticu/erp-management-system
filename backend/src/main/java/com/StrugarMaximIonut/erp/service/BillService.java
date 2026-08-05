package com.StrugarMaximIonut.erp.service;

import com.StrugarMaximIonut.erp.dto.bill.BillDTO;
import com.StrugarMaximIonut.erp.dto.bill.BillDTOMapper;
import com.StrugarMaximIonut.erp.dto.bill.BillRequestDTO;
import com.StrugarMaximIonut.erp.dto.orders.OrdersDTOMapper;
import com.StrugarMaximIonut.erp.dto.orders.OrdersRequestMapper;
import com.StrugarMaximIonut.erp.exception.bill.BillNotFoundException;
import com.StrugarMaximIonut.erp.exception.bill.BillNumberExceededException;
import com.StrugarMaximIonut.erp.exception.bill.NoBillsFoundException;
import com.StrugarMaximIonut.erp.exception.client.ClientNotFoundException;
import com.StrugarMaximIonut.erp.exception.orders.NoOrdersException;
import com.StrugarMaximIonut.erp.exception.products.NoProductsException;
import com.StrugarMaximIonut.erp.model.Bill;
import com.StrugarMaximIonut.erp.model.Client;
import com.StrugarMaximIonut.erp.model.OrderDetails;
import com.StrugarMaximIonut.erp.model.Orders;
import com.StrugarMaximIonut.erp.repository.BillRepository;
import com.StrugarMaximIonut.erp.repository.ClientRepository;
import com.StrugarMaximIonut.erp.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final OrdersService ordersService;
    private final BillDTOMapper billDTOMapper;
    private final OrdersRepository ordersRepository;
    private final OrdersDTOMapper ordersDTOMapper;
    private final OrdersRequestMapper ordersRequestMapper;
    private final ClientRepository clientRepository;

    public BillService(BillRepository billRepository, OrdersService ordersService, BillDTOMapper billDTOMapper, OrdersRepository ordersRepository, OrdersDTOMapper ordersDTOMapper, OrdersRequestMapper ordersRequestMapper, ClientRepository clientRepository){
        this.billRepository = billRepository;
        this.ordersService = ordersService;
        this.billDTOMapper = billDTOMapper;
        this.ordersRepository = ordersRepository;
        this.ordersDTOMapper = ordersDTOMapper;
        this.ordersRequestMapper = ordersRequestMapper;
        this.clientRepository = clientRepository;
    }

    public List<BillDTO> getAllBills(){
        List<Bill> list = billRepository.findAll();

        if(list.isEmpty()){
            throw new NoBillsFoundException("There are no bills in the database");
        }

        return list.stream()
                .map(billDTOMapper)
                .collect(Collectors.toList());
    }

    private Bill findBillEntityById(Integer id){
        Bill bill = billRepository.findBillByBillID(id);

        if(bill == null){
            throw new BillNotFoundException("Bill can't be found");
        }

        return bill;
    }

    public BillDTO getBillById(Integer id){
        Bill bill = findBillEntityById(id);
        return billDTOMapper.apply(bill);
    }

    public List<BillDTO> getAllBillsBetweenDates(LocalDateTime minDate, LocalDateTime maxDate){
        List<Bill> list = billRepository.findAllByBillIssueDateBetween(minDate, maxDate);

        if(list.isEmpty()){
            throw new NoBillsFoundException("Can't find bills between these dates");
        }

        return list.stream()
                .map(billDTOMapper)
                .collect(Collectors.toList());
    }

    public BillDTO getBillBySeriesAndNumber(String series, Integer number){
        Bill bill = billRepository.findByBillSeriesAndBillNumber(series, number);

        if(bill == null){
            throw new BillNotFoundException("Cannot find bill with these series and number");
        }

        return billDTOMapper.apply(bill);
    }

    public void deleteBillById(Integer id){
        Bill bill = findBillEntityById(id);

        billRepository.deleteByBillID(id);
    }

    public BillDTO insertBill(BillRequestDTO billRequestDTO){
        Orders order = ordersRepository.findById(billRequestDTO.orderID())
                .orElseThrow(() -> new NoOrdersException("Order not found"));

        if(order.getOrderDetails().isEmpty()){
            throw new NoProductsException("The order has no products");
        }

        Client client = clientRepository.findById(order.getClient().getClientID())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

                Integer maxBillNumber = billRepository.findMaxBillNumberBySeries(billRequestDTO.billSeries());
        Integer billNumber = 0;

        if(maxBillNumber == null){
            billNumber = 1;
        } else{
            billNumber = maxBillNumber + 1;
        }

        if(billNumber > 99999){
            throw new BillNumberExceededException("Bill with series " + billRequestDTO.billSeries() + " has reached it's number limit");
        }

        BigDecimal totalAmount = BigDecimal.ZERO;

        for(OrderDetails index : order.getOrderDetails()){
            BigDecimal quantity = BigDecimal.valueOf(index.getOrderDetailsQuantity());
            BigDecimal lineSum = index.getSellingPriceAtTheMoment().multiply(quantity);
            totalAmount = totalAmount.add(lineSum);
        }

        Bill bill = new Bill();
        bill.setBillIssueDate(LocalDateTime.now());
        bill.setBillSeries(billRequestDTO.billSeries());
        bill.setBillNumber(billNumber);
        bill.setOrders(order);
        bill.setClientAddress(client.getClientAddress());
        bill.setClientName(client.getClientName());
        bill.setBillTotalAmount(totalAmount);

        Bill savedBill = billRepository.save(bill);
        return billDTOMapper.apply(savedBill);
    }

}
