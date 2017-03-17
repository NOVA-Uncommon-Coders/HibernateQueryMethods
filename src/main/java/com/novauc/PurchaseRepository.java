package com.novauc;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by octavio on 3/16/17.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {

    List<Purchase> findPurchaseByCategory(String category);
//    int countByCategory(String category);

}
