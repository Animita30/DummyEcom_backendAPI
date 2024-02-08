package com.ecom.demo4_1.Service.ForBilling;

import com.ecom.demo4_1.Dto.BillingDto;
import com.ecom.demo4_1.Model.Common.Billing;
import com.ecom.demo4_1.Model.Checkout;
import com.ecom.demo4_1.Model.Common.Order;
import com.ecom.demo4_1.Model.Common.PaymentEntity;
import com.ecom.demo4_1.Repo.BillingRepository;
import com.ecom.demo4_1.Repo.CheckoutRepository;
import com.ecom.demo4_1.Repo.OrderRepository;
import com.ecom.demo4_1.Service.ForOrderHistory.OrderHistoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Override
    @Transactional
    public Billing processPayment(BillingDto billingDto) {
        Long checkoutId = billingDto.getCheckoutId();

        // Fetch checkout by checkoutId
        Checkout checkout = checkoutRepository.findById(checkoutId).orElse(null);

        if (checkout == null) {
            // Handle the case where checkout is not found
            // You may throw an exception or return an appropriate response
            return null;
        }
//Made changes here
//        Billing billing = new Billing();
//        billing.setCheckout(checkout);
        Billing billing=createBillingEntity(billingDto,checkout);

        // Retrieve the total price from the Checkout entity
  //Made changes here
//        double totalPrice = checkout.getTotalPrice();
//        billing.setTotalAmount(totalPrice);

        // Assuming a dummy payment process
        //processDummyPayment(billing);
        String realTransactionId=processRealPayment(billing);
        billing.setTransactionId(realTransactionId);

        // Save the billing record
        billingRepository.save(billing);

        orderHistoryService.createOrderHistoryEntry(billing);
        return billing;
    }

//    public void processDummyPayment(Billing billing) {
//        billing.setPaymentStatus("Success");
//        billing.setTransactionId("dummy_transaction_id");
//        billingRepository.save(billing);
//    }
    @Override
    public Order processOrder(BillingDto billingDto){
        Long checkoutId= billingDto.getCheckoutId();
        Checkout checkout=checkoutRepository.findById(checkoutId).orElse(null);
        if(checkout==null){
            return  null;
        }

 //made changes here
//        Order order=new Order();
//        order.setCheckout(checkout);
        Order order=createOrderEntity(billingDto,checkout);
 //made changes here

//        double totalPrice=checkout.getTotalPrice();
//        order.setTotalAmount(totalPrice);


        String realTransactionId=processRealPayment(order);
        order.setTransactionId(realTransactionId);
        orderRepository.save(order);
        orderHistoryService.createOrderHistoryEntry(order);
        return order;
    }
    private Billing createBillingEntity(BillingDto billingDto, Checkout checkout) {
        Billing billing = new Billing();
        billing.setCheckout(checkout);
        billing.setTotalAmount(checkout.getTotalPrice());
        // Set other billing details as needed
        return billing;
    }

    private Order createOrderEntity(BillingDto billingDto, Checkout checkout) {
        Order order = new Order();
        order.setCheckout(checkout);
        order.setTotalAmount(checkout.getTotalPrice());
        // Set other order details as needed
        return order;
    }

    public <T extends PaymentEntity> String processRealPayment(T paymentEntity){
        String realTransactionId=String.valueOf(System.currentTimeMillis());

        paymentEntity.setPaymentStatus("Success");
        return realTransactionId;
    }
}
