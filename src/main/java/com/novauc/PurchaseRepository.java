package com.novauc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;

import java.util.List;

/**
 * Created by Merlin on 3/16/17.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer>{
//public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Integer> {
//    public interface EventPagingAndSortRepository extends PagingAndSortingRepository<Purchase, Integer> {
//    public interface PurchasePagingAndSortRepository extends PagingAndSortingRepository<Purchase, Integer> {




//        Page<Purchase> findByCategory(Pageable pageable, String category);
//
//    Page<Purchase> findByDescription(Pageable pageable, String description);




//    List<Purchase> findByCustomer (Customer customer);
//    List<Purchase> findByDate (String date);
//    List<Purchase> findByCredit_card (int credit_card);
//    List<Purchase> findByCvv (int cvv);


    List<Purchase> findByCategory (String category);
//    @Query("SELECT g FROM Purchase g WHERE g.category LIKE ?1%")
//    List<Purchase> findByCategoryStartsWith(String name);

}
