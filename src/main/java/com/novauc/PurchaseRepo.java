package com.novauc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by souporman on 3/16/17.
 */
public interface PurchaseRepo extends PagingAndSortingRepository<Purchase, Integer>{
    //Page<Purchase> findByCategory(Pageable pageable,String category);

//    @Query(value = "SELECT p FROM purchases p ORDER BY p.date ASC")
//    Page<Purchase> findAllByOrderByDateAsc(Pageable pageable);
//
//    @Query(value = "SELECT p FROM purchases p ORDER BY p.date DESC")
//    Page<Purchase> findAllByOrderByDateDesc(Pageable pageable);

    //Page<Purchase> findAll(Pageable pageable, Sort sort);

    //Page<Purchase> findByCategory(Pageable pageable, String category);

}
