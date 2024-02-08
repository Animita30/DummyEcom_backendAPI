package com.ecom.demo4_1.Dto;

import lombok.Data;

@Data
public class CartItemDto {
    public Long userId;
    private Long productId;
    private int quantity;
}
