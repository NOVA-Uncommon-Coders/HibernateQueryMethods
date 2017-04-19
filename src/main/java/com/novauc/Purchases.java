package com.novauc;

import javax.persistence.*;
import java.util.Locale;

/**
 * Created by vtcurry on 3/16/17.
 */

@Entity
@Table(name = "Purchases")
public class Purchases {
    @Id
    @GeneratedValue
    int id;


    @Column(nullable = false)
    String cvv;

    @ManyToOne
    Customers customers;

    @Column(nullable = false)
    String category;

    @Column(nullable = false)
    String creditCard;

    @Column(nullable = false)
    String date;


    public Purchases(String cvv, Customers customers, String category, String creditCard, String date) {
        this.cvv = cvv;
        this.customers = customers;
        this.category = category;
        this.creditCard = creditCard;
        this.date = date;
    }

    public Purchases() {
    }
}