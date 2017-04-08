package com.novauc.services;

import com.novauc.entities.Customer;
import com.novauc.entities.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by ANVIL_OCTOPUS on 3/16/17.
 */
//public interface PurchaseRepository extends CrudRepository<Purchase, String> {
public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Integer> {
  Page<Purchase> findByCategoryOrderByDateDesc(Pageable pageable, String category);
  Page<Purchase> findAllByOrderByDateDesc(Pageable pageable);

//    List<Purchase> findBycategory(String category);
//    List<Purchase> findBydate(String date);
//    List<Purchase> findBycreditCard(String creditCard);
//    List<Purchase> findBycvv(int cvv);
//
//
//
//
//    Customer findFirstByGenre(String name);
//    int countByGenre(String email);
//    List<Customer> findByGenreOrderByNameAsc(String genre);
//
//    @Query("SELECT p FROM Purchase p WHERE p.name LIKE ?1%")
//    List<Customer> findByNameStartsWith(String name);
}

