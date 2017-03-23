package com.novauc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.Scanner;


@Controller
public class WalmartController {
    @Autowired
    CustomerRepository customers;
    @Autowired
    PurchaseRepository purchases;

    /***********************
     * GET routes
     ***********************/
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category, Integer page) {
        page = (page == null) ? 0 : page;
        PageRequest pr = new PageRequest(page, 10);
        Page<Purchase> p;
        if (category != null) {
            p = purchases.findByCategory(pr, category);
        }
        else {
            p = purchases.findAll(pr);
        }
        model.addAttribute("purchases", p);
        model.addAttribute("nextPage", page+1);
        model.addAttribute("showNext", p.hasNext());
        model.addAttribute("category", category);
        return "home";
    }

    /***********************
     * PostConstruct
     ***********************/


    @PostConstruct
    public void init() {
        try {
        Scanner scanner;
        if (customers.count() == 0 && purchases.count() == 0) {
            scanner = new Scanner(new File("customers.csv"));
            while(scanner.hasNext()){
                String[] data = scanner.nextLine().split(",");
                customers.save(new Customer(data[0], data[1]));
            }
            scanner = new Scanner(new File("purchases.csv"));
            while(scanner.hasNext()){
                String[] data = scanner.nextLine().split(",");
                purchases.save(new Purchase(checkCustomer(data[0]), data[1], data[2], data[3], data[4]));
            }
        }
    } catch (Exception e){
            System.out.println("Huston had a problem with scanning @" +  e.getMessage());
        }
    }
    public Customer checkCustomer(String id){
        Customer customer = customers.findFirstById(Integer.valueOf(id));
        if (customer == null){
            System.out.println("customer was null");
            //TODO TEST STATEMENT.
            //Used to catch mismatched ID fields or invalid entries
        }
        return customer;
    }
}
