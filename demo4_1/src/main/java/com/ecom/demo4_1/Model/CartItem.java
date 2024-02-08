package com.ecom.demo4_1.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;//cartId
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Product product;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "checkout_id")
    @JsonIgnore
    private Checkout checkout;


}
