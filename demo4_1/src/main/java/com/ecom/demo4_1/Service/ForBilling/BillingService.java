package com.ecom.demo4_1.Service.ForBilling;

import com.ecom.demo4_1.Dto.BillingDto;
import com.ecom.demo4_1.Model.Common.Billing;
import com.ecom.demo4_1.Model.Common.Order;

public interface BillingService {
    Billing processPayment(BillingDto billingDto);
    Order processOrder(BillingDto billingDto);
    //void processDummyPayment(Billing billing);
}
