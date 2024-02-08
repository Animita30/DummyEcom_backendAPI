package com.ecom.demo4_1.Service.ForCart;

import com.ecom.demo4_1.Dto.CartItemDto;
import com.ecom.demo4_1.Model.CartItem;
import com.ecom.demo4_1.Model.Checkout;
import com.ecom.demo4_1.Model.User;
import com.ecom.demo4_1.Repo.CartItemRepository;
import com.ecom.demo4_1.Repo.CheckoutRepository;
import com.ecom.demo4_1.Repo.ProductRepository;
import com.ecom.demo4_1.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Override
    public List<CartItem> getUserCartItems(Long userId) {
        return cartItemRepository.findByUserUserId(userId);
    }

    @Override
    public CartItem addCartItem(CartItemDto cartItemDto) {
        // Check if the user and product exist
        User user = userRepository.findById(cartItemDto.getUserId()).orElse(null);
        if (user == null) {
            // Handle the case where the user does not exist
            return null;
        }

        // Check if there's an existing cart item for the user and product
        CartItem existingCartItem = cartItemRepository.findByUserUserIdAndProductProdId(
                cartItemDto.getUserId(), cartItemDto.getProductId());

        if (existingCartItem != null) {
            // If it exists, update the quantity
            int newQuantity = existingCartItem.getQuantity() + cartItemDto.getQuantity();
            existingCartItem.setQuantity(newQuantity);
            return cartItemRepository.save(existingCartItem);
        } else {
            // If it doesn't exist, create a new CartItem
            CartItem cartItem = new CartItem();
            cartItem.setProduct(productRepository.getById(cartItemDto.getProductId()));
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setUser(user);

            // Create or retrieve the user's checkout
//            List<Checkout> checkouts = user.getCheckouts();
//            if (checkouts == null) {
//                checkouts = new Checkout();
//                checkouts.setUser(user);
//            }
//
//            // Add the cart item to the checkout
//            checkout.getCartItems().add(cartItem);
//
//            // Save the checkout and the cart item
//            checkoutRepository.save(checkout);
//            return cartItemRepository.save(cartItem);
//        }
            // Create or retrieve the user's checkout
            List<Checkout> checkouts = user.getCheckouts();
            Checkout checkout;

            if (checkouts == null || checkouts.isEmpty()) {
                // If the user doesn't have any checkouts, create a new one
                checkout = new Checkout();
                checkout.setUser(user);
            } else {
                // If the user has existing checkouts, you might want to choose one (e.g., the latest one)
                // Here, we simply select the first one for demonstration purposes
                checkout = checkouts.get(0);
            }

// Add the cart item to the checkout
            cartItem.setCheckout(checkout); // Set the checkout for the cart item
            checkout.getCartItems().add(cartItem);

// Save the checkout and the cart item
            checkoutRepository.save(checkout);
            return cartItemRepository.save(cartItem);
        }
    }
}

//    @Override
//    public CartItem addCartItem(CartItemDto cartItemDto) {
//        CartItem existingCartItem = cartItemRepository.findByUserUserIdAndProductProdId(
//                cartItemDto.getUserId(), cartItemDto.getProductId());
//
//        if (existingCartItem != null) {
//            // If it exists, update the quantity
//            int newQuantity = existingCartItem.getQuantity() + cartItemDto.getQuantity();
//            existingCartItem.setQuantity(newQuantity);
//            return cartItemRepository.save(existingCartItem);
//        } else {
//            // If it doesn't exist, create a new CartItem
//            CartItem cartItem = new CartItem();
//            cartItem.setProduct(productRepository.getById(cartItemDto.getProductId()));
//            cartItem.setQuantity(cartItemDto.getQuantity());
//
//            User user = userRepository.findById(cartItemDto.getUserId()).orElse(null);
//            if (user != null) {
//
//                // Check if there is an existing checkout for the user
//                Checkout existingCheckout = checkoutRepository.findByUser(user);
//
//                if (existingCheckout == null) {
//                    // If no existing checkout, create a new one
//                    Checkout checkout = new Checkout();
//                    checkout.setUser(user);
//                    checkout.setCartItems(List.of(cartItem));
//
//                    // Save the checkout to get the ID
//                    checkoutRepository.save(checkout);
//
//                    // Associate the cartItem with the checkout
//                    cartItem.setCheckout(checkout);
//                } else {
//                    // If there is an existing checkout, associate the cartItem with it
//                    cartItem.setCheckout(existingCheckout);
//                }
//
//                // Save the cartItem
//                return cartItemRepository.save(cartItem);
//            } else {
//                return null;
//            }
//        }
//    }


