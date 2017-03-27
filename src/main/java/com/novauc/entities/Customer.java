package com.novauc.entities;

import javax.persistence.*;

/**
 * Created by ANVIL_OCTOPUS on 3/16/17.
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    int id;


    @Column(nullable = false, unique = true)
    String name;


    @Column(nullable = false, unique = true)
    String email;

    public Customer() {
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;


    }
}