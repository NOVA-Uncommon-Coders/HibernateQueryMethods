package com.novauc.controllers;

import com.novauc.entities.Customer;
import com.novauc.services.CustomerRepository;
import com.novauc.entities.Purchase;
import com.novauc.services.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Merlin on 3/16/17.
 */
@Controller
public class HibernateQueryMethodsController {
    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchaseRepository purchases;

//    @RequestMapping(path = "/", method = RequestMethod.GET)

//    @RequestMapping(path = "/", method = RequestMethod.GET)
//    public String home(Model model, String category) {
//        List<Purchase> purchaseList = (ArrayList) purchases.findAll();
//        if (category != null) {
//            purchaseList = (List) purchases.findByCategory(category);
//        } else {
//            purchaseList = (List) purchases.findAll();
//        }
//        model.addAttribute("purchases", purchaseList);
//        model.addAttribute("category", category);
//        return "home";
//    }

        @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category, Integer page) {
        page = (page == null) ? 0 : page;
        PageRequest p_r = new PageRequest(page, 20);
        Page<Purchase> pur;
        if (category != null) {
//            p = purchases.findByCategory(p_r, category);
            pur = purchases.findByCategoryOrderByDate(p_r, category);
        }
        else {
//            p = purchases.findAll(pr);
            pur = purchases.findAllByOrderByDate(p_r);
        }
        model.addAttribute("purchases", pur);
        model.addAttribute("nextPage", page+1);
        model.addAttribute("showNext", pur.hasNext());
        model.addAttribute("category", category);


        return "home";
    }


    @PostConstruct
    public void init() throws IOException {


        File data1 = new File("customers.csv");
        Scanner ss1 = new Scanner(data1);

        File data2 = new File("purchases.csv");
        Scanner ss2 = new Scanner(data2);

        if (customers.count() == 0) {
            while (ss1.hasNext()) {
                String line1 = ss1.nextLine();
                String[] delineators = line1.split(",");
                Customer customer = new Customer(delineators[0], delineators[1]);
                customers.save(customer);
            }
            if (purchases.count() == 0) {
                while (ss2.hasNext()) {
                    String line2 = ss2.nextLine();
                    String[] delineators = line2.split(",");
                    Purchase purchase = new Purchase(delineators[1], delineators[2], delineators[3], delineators[4], customers.findOne(Integer.valueOf(delineators[0])));
                    purchases.save(purchase);
                }
            }
        }


    }
}