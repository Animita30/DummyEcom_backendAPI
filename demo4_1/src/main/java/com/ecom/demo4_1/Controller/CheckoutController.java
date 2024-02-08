package com.ecom.demo4_1.Controller;

import com.ecom.demo4_1.Dto.CheckoutDto;
import com.ecom.demo4_1.Model.Checkout;
import com.ecom.demo4_1.Service.ForCheckOut.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/login/cart/checkout")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @PostMapping
    public Checkout processCheckout(@RequestBody CheckoutDto checkoutDto)
    {
        System.out.println("Received CheckoutDto: " + checkoutDto);
        return checkoutService.processCheckout(checkoutDto);
    }
}
