package com.novauc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by jerieshasmith on 3/16/17.
 */
public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Integer> {
    Page<Purchase> findByCategory(Pageable pageable, String category);



}
