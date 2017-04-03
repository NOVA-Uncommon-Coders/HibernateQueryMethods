package com.novauc.services;

import com.novauc.entities.Customer;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Merlin on 3/16/17.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

//    List<Customer> findByCustomer(String name);
//    List<Customer> findByEmail (String email);
//Customer findFirstByName(String customerName);
//
//
//    Customer findFirstByGenre(String name);
//    int countByGenre(String email);
//    List<Customer> findByGenreOrderByNameAsc(String genre);
//
//    @Query("SELECT c FROM Customer c WHERE c.name LIKE ?1%")
//    List<Customer> findByNameStartsWith(String name);
//
}
