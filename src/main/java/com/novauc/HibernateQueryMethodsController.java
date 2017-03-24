package com.novauc;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category) {
        List<Purchase> purchaseList = (ArrayList) purchases.findAll();
        if (category != null) {
            purchaseList = (List) purchases.findByCategory(category);
        } else {
            purchaseList = (List) purchases.findAll();
        }
        model.addAttribute("purchases", purchaseList);
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