package com.novauc;

import javax.persistence.*;

/**
 * Created by jerieshasmith on 3/16/17.
 */
@Entity
@Table(name = "purchases")
public class Purchase {

    @ManyToOne
    Customer customer;


    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    String creditCard;

    @Column(nullable = false)
    String cvv;

    @Column(nullable = false)
    String category;


    public Purchase(){

    }





    public void setDate(String date) {
        this.date = date;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Purchase(Customer customer,String date, String creditCard, String cvv, String category ) {
       this.customer = customer;
       this.date = date;
        this.creditCard = creditCard;
        this.cvv = cvv;
        this.category = category;


    }


}
