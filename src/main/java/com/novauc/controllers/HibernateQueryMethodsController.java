package com.novauc.controllers;

import com.novauc.entities.Customer;
import com.novauc.entities.Purchase;
import com.novauc.services.CustomerRepository;
import com.novauc.services.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by ANVIL_OCTOPUS on 3/16/17.
 */


@Controller
public class HibernateQueryMethodsController {
    @Autowired
    CustomerRepository customers;
    @Autowired
    PurchaseRepository purchases;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category) {
        List<Purchase> purchasesList = (ArrayList) purchases.findAll();
        if (category != null) {
            purchasesList = (List) purchases.findBycategory(category);
        } else {
            purchasesList = (List) purchases.findAll();
        }
        model.addAttribute("purchases", purchasesList);
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
                String[] section = line1.split(",");
                Customer customer = new Customer(section[0], section[1]);
                customers.save(customer);
            }
            if (purchases.count() == 0) {
                while (ss2.hasNext()) {
                    String line2 = ss2.nextLine();
                    String[] section = line2.split(",");
                    Purchase purchase = new Purchase(section[1], section[2], section[3], section[4], customers.findOne(Integer.valueOf(section[0])));
                    purchases.save(purchase);
                }
            }


        }
    }
}