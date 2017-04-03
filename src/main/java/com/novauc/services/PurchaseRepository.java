package com.novauc.services;

import com.novauc.entities.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Merlin on 3/16/17.
 */
//public interface PurchaseRepository extends CrudRepository<Purchase, Integer>{
//public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Integer> {


//    Page<Purchase> findByCategory(Pageable pageable, String category);



//    Page<Purchase> findByCategoryThenDate(Pageable pageable, String category);
//    Page<Purchase> findAllOrderDate(Pageable pageable);



    Page<Purchase> findByCategoryOrderByDate(Pageable pageable, String category);
    Page<Purchase> findAllByOrderByDate(Pageable pageable);




//    public interface EventPagingAndSortRepository extends PagingAndSortingRepository<Purchase, Integer> {
//    public interface PurchasePagingAndSortRepository extends PagingAndSortingRepository<Purchase, Integer> {




//        Page<Purchase> findByCategory(Pageable pageable, String category);
//
//    Page<Purchase> findByDescription(Pageable pageable, String description);




//    List<Purchase> findByCustomer (Customer customer);
//    List<Purchase> findByDate (String date);
//    List<Purchase> findByCredit_card (int credit_card);
//    List<Purchase> findByCvv (int cvv);



//this below is what I tried to use....
//    List<Purchase> findByCategory (String category);



//    @Query("SELECT g FROM Purchase g WHERE g.category LIKE ?1%")
//    List<Purchase> findByCategoryStartsWith(String name);

}
