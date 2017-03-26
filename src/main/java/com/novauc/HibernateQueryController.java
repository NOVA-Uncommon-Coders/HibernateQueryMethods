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
    @PostConstruct
    public void init() throws FileNotFoundException {
        try {
            File file;
            Scanner scanner;
            if (customers.count() == 0 || purchases.count() == 0) {
                file = new File("customers.csv");
                scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    String[] columns = line.split(",");
                    customers.save(new Customer(columns[0], columns[1]));

                }
            }
            if (purchases.count() == 0) {
                file = new File("purchases.csv");
                scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    String[] columns = line.split(",");

                    purchases.save(new Purchase(addCustomer(columns[0]), columns[1], columns[2], columns[3], columns[4]));
                }
            }
        } catch (Exception e) {
            System.out.println();

        }
    }

    public Customer addCustomer(String id){
        Customer customer = customers.findFirstById(Integer.valueOf(id));


        return customer;


    }

}
