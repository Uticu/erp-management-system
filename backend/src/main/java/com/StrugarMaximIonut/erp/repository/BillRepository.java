package com.StrugarMaximIonut.erp.repository;

import com.StrugarMaximIonut.erp.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    Bill findBillByBillID(Integer id);

    List<Bill> findAllByBillIssueDateBetween(LocalDateTime billIssueDateAfter, LocalDateTime billIssueDateBefore);

    Bill findByBillSeriesAndBillNumber(String series, Integer number);

    void deleteByBillID(Integer billID);

    @Query("SELECT MAX(b.billNumber)" +
            " FROM Bill b " +
            " WHERE b.billSeries = :series")
    Integer findMaxBillNumberBySeries(@Param("series") String series);
}
