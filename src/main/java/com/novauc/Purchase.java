package com.novauc;

import javax.persistence.*;

/**
 * Created by souporman on 3/16/17.
 */
@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    Customer customer;

    @Column (nullable = false)
    String date;

    @Column (nullable = false)
    String credit_card;

    @Column (nullable = false, length = 10)
    int cvv;

    @Column (nullable = false)
    String category;

    public Purchase() {
    }

    public Purchase(Customer customer, String date, String credit_card, int cvv, String category) {
        this.customer = customer;
        this.date = date;
        this.credit_card = credit_card;
        this.cvv = cvv;
        this.category = category;
    }
}
