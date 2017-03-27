package com.novauc;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by jerieshasmith on 3/16/17.
 */
public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    Customer findFirstById(int id);

}
