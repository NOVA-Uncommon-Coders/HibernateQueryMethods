package com.novauc;

import javax.persistence.*;

/**
 * Created by dangelojoyce on 3/16/17.
 */
@Entity
@Table (name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    String credit_card;

    @Column(nullable = false)
    String cvv;

    @Column(nullable = false)
    String category;


    @ManyToOne
    Customer customer;

    public Purchase(){

    }

    public Purchase (String date, String credit_card, String cvv, String category, Customer customer){
        this.date = date;
        this.credit_card = credit_card;
        this.cvv = cvv;
        this.category = category;
        this.customer = customer;

    }

    public Purchase(String date){
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString(){
        return (date + "    " +  credit_card + "   " + cvv + " " + category + "   " + customer); //concatenating done or not based on the value of isDone variable +
    }

}
