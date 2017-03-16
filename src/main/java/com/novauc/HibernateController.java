package com.novauc;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Controller
public class HibernateController {

    @Autowired
    PurchaseRepo purchases;
    @Autowired
    CustomerRepo customers;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String sorter) {
        List<Purchase> purchaseList = (List) purchases.findAll();
        if(sorter!=null){
            if (sorter.equalsIgnoreCase("all")) {
                purchaseList = (List) purchases.findAll();
                model.addAttribute("purchases", purchaseList);
            } else if (!sorter.equalsIgnoreCase("all")) {
                purchaseList = purchases.findByCategory(sorter);
                model.addAttribute("purchases", purchaseList);
            }

        }
        model.addAttribute("purchases", purchaseList);
        return "home";
    }

    @PostConstruct
    void enterPurchases() throws IOException {
        Path pathToCustomers = Paths.get("/Users/souporman/Code/HibernateQueryMethods/customers.csv");
        Scanner customerReader = new Scanner(pathToCustomers);
        if(customers.count()==0) {
            while (customerReader.hasNext()) {
                String lineC = customerReader.nextLine();
                String[] attributes = lineC.split(",");
                Customer customer = new Customer(attributes[0], attributes[1]);
                customers.save(customer);
            }
        }

        Path pathToPurchases = Paths.get("/Users/souporman/Code/HibernateQueryMethods/purchases.csv");
        Scanner purchaseReader = new Scanner(pathToPurchases);
        if(purchases.count()==0) {
            while (purchaseReader.hasNext()) {
                String lineP = purchaseReader.nextLine();
                String[] attributes = lineP.split(",");
                Purchase purchase = new Purchase(customers.findFirstById(Integer.valueOf(attributes[0])),attributes[1], attributes[2],Integer.valueOf(attributes[3]),attributes[4]);
                purchases.save(purchase);
            }
        }

    }

}
