package com.ecom.demo4_1.Repo;

import com.ecom.demo4_1.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findByUserUserId(Long userId);

    CartItem findByUserUserIdAndProductProdId(Long userId, Long productId);
}
