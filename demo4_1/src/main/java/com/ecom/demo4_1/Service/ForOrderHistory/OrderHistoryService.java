package com.ecom.demo4_1.Service.ForOrderHistory;

import com.ecom.demo4_1.Model.Common.Billing;
import com.ecom.demo4_1.Model.Common.Order;
import com.ecom.demo4_1.Dto.OrderHistoryDto;
import com.ecom.demo4_1.Model.Common.PaymentEntity;

import java.util.List;

public interface OrderHistoryService {
    List<Order> getOrderHistory(Long userId);

    List<Order> searchOrderHistory(OrderHistoryDto orderHistoryDto);

    void createOrderHistoryEntry(PaymentEntity paymentEntity);
    // Additional methods related to order history can be added as needed
}
