package com.ecom.demo4_1.Service.ForCheckOut;

import com.ecom.demo4_1.Dto.CheckoutDto;
import com.ecom.demo4_1.Model.Checkout;

import java.util.List;

public interface CheckoutService {
    Checkout processCheckout(CheckoutDto checkoutDto);
    List<Checkout> getCheckoutDetailsByUserId(Long userId);
}
