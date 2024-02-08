package com.ecom.demo4_1.Controller;

import com.ecom.demo4_1.Dto.CartItemDto;
import com.ecom.demo4_1.Model.CartItem;
import com.ecom.demo4_1.Service.ForCart.CartService;
import com.ecom.demo4_1.Service.ForUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/login/cart")
public class CartController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @PostMapping("add-cart-items")
    public CartItem addCartItem(@RequestBody CartItemDto cartItemDto){

        return cartService.addCartItem(cartItemDto);
    }

    @GetMapping("cart-items/get")
    public List<CartItem> getUserCartItems(@RequestParam Long userId) {

        return cartService.getUserCartItems(userId);
    }



}
