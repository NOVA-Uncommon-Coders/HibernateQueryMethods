package com.novauc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by octavio on 3/16/17.
 */



@Controller
public class HibernateQueryMethodsController {


    @Autowired
    PurchaseRepository purchases;
    @Autowired
    CustomerRepository customers;



    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category) {
        List<Purchase> purchaseList = (ArrayList) purchases.findAll();
        if (category != null){
            purchaseList = (List)purchases.findPurchaseByCategory(category);
        }
        else {
            purchaseList = (List)purchases.findAll();
        }
        model.addAttribute("purchases", purchaseList);
        return "home";
    }

    @PostConstruct
    public void init() throws IOException {

//        if (customers.count() < 5) {
//            Customer customer = new Customer();
//            customer.name = "Zach";
////          customer.password = "hunter2";
//            customers.save(customer);


        File c = new File("customers.csv");
        Scanner scanner = new Scanner(c);

        File p = new File("purchases.csv");
        Scanner scanner1 = new Scanner(p);

        if (customers.count() == 0) {                       //stop from duplicating
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
//          System.out.println(line);
                String[] columns = line.split(",");
                Customer customer = new Customer(columns[0], columns[1]);//object for people
                customers.save(customer);
            }
            if (purchases.count() == 0) {
                while (scanner1.hasNext()) {
                    String line1 = scanner1.nextLine();
                    String[] columns = line1.split(",");
                    Purchase purchase = new Purchase(columns[1], columns[2], columns[3], columns[4], customers.findOne(Integer.valueOf(columns[0]))); //object for purchases
                    purchases.save(purchase);
                }


//          System.out.println("This person's name is " + line);

//            if (peopleMap.containsKey(people.getCountry())) {
//
//                peopleMap.get(columns[4]).add(people);
//            }
//
//            else {
//                peopleMap.put(people.getCountry(), new ArrayList<>());
//                peopleMap.get(columns[4]).add(people);
//
//            }
            }

//        System.out.println(peopleMap);


        }

    }
}


