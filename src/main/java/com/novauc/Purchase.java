package com.novauc;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
public class Purchase {

    @ManyToOne
    Customer customer;

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String creditCard;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false)
    private String category;

    public Purchase(Customer customer, String date, String creditCard, String cvv, String category) {
        this.customer = customer;
        this.date = date;
        this.creditCard = creditCard;
        this.cvv = cvv;
        this.category = category;
    }

    public Purchase() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCvv() {
        return cvv;
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
}
