package com.ecom.demo4_1.Service.ForCart;

import com.ecom.demo4_1.Dto.CartItemDto;
import com.ecom.demo4_1.Model.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getUserCartItems(Long userId);
    CartItem addCartItem(CartItemDto cartItemDto);
}
