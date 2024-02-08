package com.ecom.demo4_1.Model.Common;

import com.ecom.demo4_1.Model.Checkout;
import com.ecom.demo4_1.Model.OrderHistory;
import com.ecom.demo4_1.Model.Product;
import com.ecom.demo4_1.Model.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

@Data
@Entity
@Table(name = "my_order")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Order implements PaymentEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "checkout_id",unique = true)
    private Checkout checkout;

    @OneToOne
    @JoinColumn(name = "billing_id", unique = true)
    private Billing billing;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderHistory> orderHistory;

    @ManyToMany
    @JoinTable(name = "order_product",joinColumns = @JoinColumn(name = "order_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    private double totalAmount;
    private String paymentStatus;
    private String transactionId;

}
