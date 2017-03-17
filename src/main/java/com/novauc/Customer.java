package com.novauc;

import javax.persistence.*;

/**
 * Created by jerieshasmith on 3/16/17.
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String name;


    @Column(nullable = false)
    String emailAddress;

    public Customer() {
    }

    public Customer( String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}


