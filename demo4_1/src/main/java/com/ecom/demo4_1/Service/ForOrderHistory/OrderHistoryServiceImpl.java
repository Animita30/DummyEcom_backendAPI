package com.ecom.demo4_1.Service.ForOrderHistory;

import com.ecom.demo4_1.Dto.OrderHistoryDto;
import com.ecom.demo4_1.Model.Checkout;
import com.ecom.demo4_1.Model.Common.Billing;
import com.ecom.demo4_1.Model.Common.Order;
import com.ecom.demo4_1.Model.Common.PaymentEntity;
import com.ecom.demo4_1.Model.OrderHistory;
import com.ecom.demo4_1.Repo.OrderHistoryRepository;
import com.ecom.demo4_1.Repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Override
    public List<Order> getOrderHistory(Long userId) {
        // Implement logic to retrieve order history based on userId
        // Use the custom query method from OrderRepository
        return orderRepository.findByUserUserId(userId);
    }

    @Override
    public List<Order> searchOrderHistory(OrderHistoryDto orderHistoryDto) {
        // Implement logic to search order history based on the provided criteria in orderHistoryDto

        Long userId = orderHistoryDto.getUserId();

        if (userId != null) {
            // If userId is provided, search for orders for that specific user
            return orderRepository.findByUserUserId(userId);
        } else {
            // If userId is not provided, you might want to handle this case accordingly
            // Here, I'm returning an empty list, but you can adjust it based on your requirements
            return Collections.emptyList();
        }
    }
    @Override
    public void createOrderHistoryEntry(PaymentEntity paymentEntity) {
        // Extract relevant information from the Billing entity
        if (paymentEntity instanceof Billing) {
            Billing billing = (Billing) paymentEntity;
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setOrder(billing.getOrder()); // Associate with the order from billing
            orderHistory.setTotalAmount(billing.getTotalAmount()); // Set other relevant fields
            orderHistory.setTransactionId(billing.getTransactionId());
            orderHistoryRepository.save(orderHistory);
            // Extract billing information and create OrderHistory entry
        } else if (paymentEntity instanceof Order) {
            Order order = (Order) paymentEntity;
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setOrder(order); // Associate with the provided order
            orderHistory.setTotalAmount(order.getTotalAmount()); // Set other relevant fields
            orderHistory.setTransactionId(order.getTransactionId());
            orderHistoryRepository.save(orderHistory);
            // Extract order information and create OrderHistory entry
        }

        System.out.println("Order history entry created");
    }




}
