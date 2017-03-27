package com.novauc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by octavio on 3/16/17.
 */

//public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {

public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Integer>{

    Page<Purchase> findByCategoryOrderByDateDesc(Pageable pageable, String category);
    Page<Purchase> findAllByOrderByDateDesc(Pageable pageable);
}
//    List<Purchase> findPurchaseByCategory(String category);
//    int countByCategory(String category);


