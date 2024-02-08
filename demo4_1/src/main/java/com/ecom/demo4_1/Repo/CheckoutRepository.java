package com.ecom.demo4_1.Repo;

import com.ecom.demo4_1.Model.Checkout;
import com.ecom.demo4_1.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    Checkout findByUser(User user);
    List<Checkout> findByUser_UserId(Long userId);
}
