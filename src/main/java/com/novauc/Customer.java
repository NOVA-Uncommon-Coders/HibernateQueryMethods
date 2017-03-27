package com.novauc;

import javax.persistence.*;

/**
 * Created by octavio on 3/16/17.
 */
@Entity //represents an entity in a persistent class...represents database records in a data user//
@Table(name = "customers")


public class Customer {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true) //when inserting name to the database, name cannot be null
            String name;

//    @Column(nullable = false) // similarly, password cannot be null
//            String password;

    @Column(nullable = false, unique = true)
            String email;

    public Customer(){
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
