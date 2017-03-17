package com.novauc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jerieshasmith on 3/16/17.
 */
@Controller
public class HibernateQueryController {
    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchaseRepository purchases;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(Model model, String category, String name,String search) {
        List<Purchase> purchaseList;
        if (name != null){
            purchaseList =  purchases.findByName(name);
        } else if (category != null) {
            purchaseList = purchases.findAllByCategory(category);
        }
        else if (search != null) {
                purchaseList = purchases.findByName(search);

        } else {
            purchaseList= (ArrayList) purchases.findAll();
        }

        model.addAttribute("purchases", purchaseList);
        return "home";
    }


    @PostConstruct
    public void init() {
        try {
            File file;
            Scanner scanner;
            if (customers.count() == 0) {
                file = new File("customers.csv");
                scanner = new Scanner(file);
                scanner.useDelimiter("\n");
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    String[] columns = line.split(",");
                    Customer customer = new Customer(String.valueOf(columns[0]), columns[1]);
                    customers.save(customer);
                }
            }
            if (purchases.count() == 0) {
                file = new File("purchases.csv");
                scanner = new Scanner(file);
                scanner.useDelimiter("\n");
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    String[] columns = line.split(",");
                    Purchase purchase = new Purchase(String.valueOf(columns[0]), columns[1], columns[2], columns[3],columns[4]);
                    purchases.save(purchase);
                }
            }
        } catch (Exception e) {
            System.out.println();


        }
            return;


    }

}
