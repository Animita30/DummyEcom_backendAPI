package com.ecom.demo4_1.Service.ForCheckOut;

import com.ecom.demo4_1.Dto.CheckoutDto;
import com.ecom.demo4_1.Model.CartItem;
import com.ecom.demo4_1.Model.Checkout;
import com.ecom.demo4_1.Model.Product;
import com.ecom.demo4_1.Model.User;
import com.ecom.demo4_1.Repo.CartItemRepository;
import com.ecom.demo4_1.Repo.CheckoutRepository;
import com.ecom.demo4_1.Repo.ProductRepository;
import com.ecom.demo4_1.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CheckoutServiceImpl implements CheckoutService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CheckoutRepository checkoutRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public  List<Checkout> getCheckoutDetailsByUserId(Long userId) {
        return checkoutRepository.findByUser_UserId(userId);
    }

//    @Override
//    public Checkout processCheckout(CheckoutDto checkoutDto) {
//        Long userId = checkoutDto.getUserId();
//        User user = userRepository.findById(userId).orElse(null);
//
//        if (user != null) {
//            //Map<Long, Integer> productQuantities = checkoutDto.getProductQuantities();
//
//            // Calculate total price based on selected products and quantities
//            //double totalPrice = calculateTotalPrice(productQuantities);
//
//            // Create a new Checkout instance and associate it with the user, cart items, and total price
//            Checkout checkout = new Checkout();
//            checkout.setUser(user);
//            //checkout.setTotalPrice(totalPrice);
//
//
//
//            // Create CartItem instances and associate them with the Checkout
//            List<CartItem> cartItems = cartItemRepository.findByUserUserId(userId);
//
//            for (CartItem cartItem : cartItems) {
//                cartItem.setCheckout(checkout);
//            }
//
//            checkout.setCartItems(cartItems);
//            double totalPrice = calculateTotalPrice(checkout.getCartItems());
//            checkout.setTotalPrice(totalPrice);
//
//            // Save the Checkout entity with associated CartItem entities
//            return checkoutRepository.save(checkout);
//
//        } else {
//            // Handle the case when the user is not found
//            return null;
//        }
//    }
@Override
public Checkout processCheckout(CheckoutDto checkoutDto) {
    Long userId = checkoutDto.getUserId();
    User user = userRepository.findById(userId).orElse(null);

    if (user != null) {
        // Check if there's an existing checkout for the user
        Checkout existingCheckout = checkoutRepository.findByUser(user);

        if (existingCheckout != null) {
            // Update existing checkout with new cart items and total price
            List<CartItem> cartItems = cartItemRepository.findByUserUserId(userId);
            existingCheckout.getCartItems().clear(); // Remove existing cart items
            existingCheckout.getCartItems().addAll(cartItems);
            double totalPrice = calculateTotalPrice(cartItems);
            existingCheckout.setTotalPrice(totalPrice);

            // Save the updated checkout
            return checkoutRepository.save(existingCheckout);
        } else {
            // Create a new Checkout instance and associate it with the user, cart items, and total price
            Checkout newCheckout = new Checkout();
            newCheckout.setUser(user);

            List<CartItem> cartItems = cartItemRepository.findByUserUserId(userId);
            for (CartItem cartItem : cartItems) {
                cartItem.setCheckout(newCheckout);
            }

            newCheckout.setCartItems(cartItems);
            double totalPrice = calculateTotalPrice(cartItems);
            newCheckout.setTotalPrice(totalPrice);

            // Save the new Checkout entity with associated CartItem entities
            return checkoutRepository.save(newCheckout);
        }
    } else {
        // Handle the case when the user is not found
        return null;
    }
}

    private double calculateTotalPrice(List<CartItem> cartItems) {//(Map<Long, Integer> productQuantities) {
        double totalPrice = 0;

//        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
//            Long productId = entry.getKey();
//            Integer quantity = entry.getValue();
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            // Fetch product from the database
            //Product product = productRepository.findById(productId).orElse(null);

            if (product != null) {
                // Add product price multiplied by quantity to the total price
                totalPrice += product.getPrice() * quantity;
            }
        }

        return totalPrice;
    }


}
