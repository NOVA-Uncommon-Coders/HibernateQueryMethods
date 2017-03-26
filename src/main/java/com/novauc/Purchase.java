package com.novauc;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

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
    Date date;

    @Column (nullable = false)
    String credit_card;

    @Column (nullable = false, length = 10)
    int cvv;

    @Column (nullable = false)
    String category;

    public Purchase() {
    }

    public Purchase(Customer customer, Date date, String credit_card, int cvv, String category) {
        this.customer = customer;
        this.date = date;
        this.credit_card = credit_card;
        this.cvv = cvv;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }

    public String getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
