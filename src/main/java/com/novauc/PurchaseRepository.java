package com.novauc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Integer> {
    Page<Purchase> findByCategoryOrderByDateDesc(Pageable pageable, String category);
    Page<Purchase> findAllByOrderByDateDesc(Pageable pageable);

//    findAllByOrderByDateTimeAsc
}