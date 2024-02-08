package com.ecom.demo4_1.Model.Common;

import com.ecom.demo4_1.Model.Checkout;
import com.ecom.demo4_1.Model.Product;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property ="id" )
public class Billing implements PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "checkout_id", unique = true)
    private Checkout checkout;

    @OneToOne(mappedBy = "billing", cascade = CascadeType.ALL)
    private Order order;

    @ManyToMany
    @JoinTable(name = "billing_product",
            joinColumns = @JoinColumn(name = "billing_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    private double totalAmount;
    private String paymentStatus;
    private String transactionId;
}
