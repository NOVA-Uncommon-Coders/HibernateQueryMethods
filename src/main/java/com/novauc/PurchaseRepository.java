package com.novauc;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    List<Purchase> findAllByCategory(String category);
    List<Purchase> findAllByCustomer(Customer customer);
    @Query ("SELECT p FROM Purchase p WHERE p.customer.name LIKE ?1%")
    List<Purchase> findByName(String name);

}