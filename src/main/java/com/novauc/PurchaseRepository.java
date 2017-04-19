package com.novauc;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by vtcurry on 3/16/17.
 */
public interface PurchaseRepository extends CrudRepository<Purchases, Integer>{
    List<Purchases> findByCategory(String category);
}
