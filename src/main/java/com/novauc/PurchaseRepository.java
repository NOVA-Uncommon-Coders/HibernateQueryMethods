package com.novauc;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Integer> {
    //Page<Purchase> SortByDate(Pageable pageable, String category);
    //List<Purchase> findByCategorySortByDate(String category);
   // List<Purchase> findAllByOrderByDateTimeDesc(Pageable pageable, String category, LocalDateTime dt);
    Page<Purchase> findByCategoryOrderByDateDesc(Pageable pageable, String category);


//    Page<Purchase> SortByDate(Pageable pageable, String category);
//    List<Purchase> SortByDate(Long category);

//    @Query("SELECT p FROM Purchase p WHERE p.customer.name LIKE ?1%")
//    List<Purchase> findByName(String name);

}