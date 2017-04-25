package com.novauc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;



/**
 * Created by vtcurry on 3/16/17.
 */
public interface PurchaseRepository extends PagingAndSortingRepository<Purchases, Integer> {
    Page<Purchases> findByCategoryOrderByDateDesc (Pageable pageable, String category);
}
