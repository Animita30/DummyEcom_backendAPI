package com.ecom.demo4_1.Repo;

import com.ecom.demo4_1.Dto.OrderHistoryDto;
import com.ecom.demo4_1.Model.Common.Order;
import com.ecom.demo4_1.Model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory,Long> {
    //List<OrderHistory> findByOrder_OrderDateBetween(LocalDate startDate, LocalDate endDate);
    List<OrderHistory> findByOrder_UserUserId(Long userId);
}
