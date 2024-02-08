package com.ecom.demo4_1.Dto;

import lombok.Data;

import java.util.Map;

@Data
public class CheckoutDto {
    private Long userId;
    //private Map<Long,Integer> productQuantities;//because i want to checkout using userId alone
    //private Long checkoutId;
}
