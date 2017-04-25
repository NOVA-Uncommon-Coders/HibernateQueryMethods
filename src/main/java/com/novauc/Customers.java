package com.novauc;

import javax.persistence.*;

/**
 * Created by vtcurry on 3/16/17.
 */

@Entity
@Table(name = "Customers")
public class Customers {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false)
    String email;

    public Customers(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Customers() {
    }
}