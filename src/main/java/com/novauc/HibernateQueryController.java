package com.novauc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by vtcurry on 4/19/17.
 */
@Controller
public class HibernateQueryController {

@Autowired
CustomerRepository customer;

@Autowired
PurchaseRepository purchase;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(String category, Model model) {
        List<Purchases>results = null;
        if(category != null){
            results = (List)purchase.findByCategory(category);
        }else {
            results = (List)purchase.findAll();
        }
        model.addAttribute("purchase", results);
        return "Home";
    }
@PostConstruct
    public void init()throws IOException{
        File customers= new File("customers.csv");
        File purchases= new File("purchases.csv");
        Scanner cust= new Scanner (customers);
        Scanner purch= new Scanner (purchases);
        if (customer.count() == 0) {
            while (cust.hasNext()) {
                String line = cust.nextLine();
                String[] ball = line.split(",");
                Customers john = new Customers(ball[0], ball[1]);
                customer.save(john);
            }
        }
    if (purchase.count() == 0) {
        while (purch.hasNext()) {
            String line = purch.nextLine();
            String[] ball = line.split(",");
            Purchases john = new Purchases(ball[3], customer.findOne(Integer.valueOf(ball[0])), ball[4], ball[2], ball [1]);
            purchase.save(john);
        }
    }
}
}
