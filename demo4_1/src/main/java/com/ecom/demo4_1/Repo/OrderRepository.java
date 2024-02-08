package com.ecom.demo4_1.Repo;

import com.ecom.demo4_1.Model.Common.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    // Find orders by user ID
    List<Order> findByUserUserId(Long userId);

}
