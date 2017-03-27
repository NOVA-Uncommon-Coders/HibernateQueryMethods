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
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by dangelojoyce on 3/16/17.
 */

@Controller
public class HibernateQueryMethodsController {

    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchaseRepository purchases;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category, Integer page) {
//        List<Purchase> purchaseList;
//        if (category != null) {
//            purchaseList = purchases.findByCategory(category);
//        } else {
//            List<Customer> customerList = (ArrayList) customers.findAll();
//            model.addAttribute("customers", customerList);
//
//            purchaseList = (ArrayList) purchases.findAll();
//        }

        page = (page == null) ? 0 : page;
        PageRequest pr = new PageRequest(page, 5);
        Page<Purchase> p;
        if (category != null) {
            p = purchases.findByCategoryOrderByDateDesc(pr, category);
        } else {
            p = purchases.findAll(pr);
        }
            model.addAttribute("purchases", p);
            model.addAttribute("nextPage", page + 1);
            model.addAttribute("showNext", p.hasNext());
            model.addAttribute("category", category);
//        model.addAttribute("dateTime", dt);

        return "home";
    }





    @PostConstruct
    public void init() throws IOException{
       File fp = new File ("purchases.csv");
       File fc = new File ("customers.csv");
        Scanner scanner = new Scanner(fc);
        if (customers.count() == 0) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                Customer customer = new Customer(String.valueOf(columns[0]), columns[1]);
                customers.save(customer);
            }
        }
        Scanner scannerp = new Scanner(fp);
        if(purchases.count() == 0){
            while (scannerp.hasNext()){
                String line = scannerp.nextLine();
                String[] columns = line.split(",");
                Purchase purchase = new Purchase(columns[1], columns[2], columns[3], columns[4], customers.findOne((Integer.parseInt(columns[0]))));
                purchases.save(purchase);

            }
        }


    }






}
