package com.ecom.demo4_1.Controller;

import com.ecom.demo4_1.Dto.OrderHistoryDto;
import com.ecom.demo4_1.Model.Common.Order;
import com.ecom.demo4_1.Service.ForOrderHistory.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order/history")
public class OrderHistoryController {
    @Autowired
    private OrderHistoryService orderHistoryService;
    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long userId) {
        List<Order> orderHistory = orderHistoryService.getOrderHistory(userId);
        return new ResponseEntity<>(orderHistory, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Order>> searchOrderHistory(@RequestBody OrderHistoryDto orderHistoryDto) {
        List<Order> orderHistory = orderHistoryService.searchOrderHistory(orderHistoryDto);
        return new ResponseEntity<>(orderHistory, HttpStatus.OK);
    }

}
