package com.ecom.demo4_1.Controller;

import com.ecom.demo4_1.Dto.BillingDto;
import com.ecom.demo4_1.Model.Common.Billing;
import com.ecom.demo4_1.Model.Common.Order;
import com.ecom.demo4_1.Service.ForBilling.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/billing")
public class BillingController {
    @Autowired
    private BillingService billingService;
    @PostMapping("/process-payment")
    public Billing processPayment(@RequestBody BillingDto billingDto)
    {
        return billingService.processPayment(billingDto);
    }
    @PostMapping("/process-order")
    public Order processOrder(@RequestBody BillingDto billingDto){
        return billingService.processOrder(billingDto);
    }
}
