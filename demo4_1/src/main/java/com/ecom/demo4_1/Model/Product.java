package com.ecom.demo4_1.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "prodId")
    private Long prodId;
    private String prodname;
    private double price;

}
