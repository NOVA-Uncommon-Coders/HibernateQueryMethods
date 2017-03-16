package com.novauc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
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
    public String index(Model model, String category, String customerName) {
        List<Purchase> purchaseList;
        if (customerName != null){
            //purchaseList = (List<Purchase>) purchases.findAllByCustomer(customers.findFirstByName(customerName));
            purchaseList = (List<Purchase>) purchases.findByName(customerName);
        } else if (category != null){
            purchaseList = (List<Purchase>) purchases.findAllByCategory(category);
        } else {
            purchaseList= (List<Purchase>) purchases.findAll();
        }

        model.addAttribute("purchases", purchaseList);
        return "home";
    }

    /***********************
     * PostConstruct
     ***********************/


    @PostConstruct
    public void init() {
        try {
        File file;
        Scanner scanner;
        if (customers.count() == 0) {
            file = new File("customers.csv");
            scanner = new Scanner(file);
            scanner.useDelimiter("\n");
            while(scanner.hasNext()){
                String[] data = scanner.next().split(",");
                customers.save(new Customer(data[0], data[1]));
            }
        }        if (purchases.count() == 0) {
                file = new File("purchases.csv");
                scanner = new Scanner(file);
                scanner.useDelimiter("\n");
                while(scanner.hasNext()){
                    String[] data = scanner.next().split(",");
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
            //TODO TEST STATEMENT
        }
        return customer;
    }
}
