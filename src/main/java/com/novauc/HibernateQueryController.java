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

    @Controller
    public class PurchasesController {

        @RequestMapping(path = "/", method = RequestMethod.GET)
        public String home(Model model, String category, Integer page) {
            page = (page == null) ? 0 : page;
            PageRequest pr = new PageRequest(page, 10);
            Page<Purchases> p;
            if (category != null) {
                p = purchase.findByCategoryOrderByDateDesc(pr, category);
            }
            else {
                p = purchase.findAll(pr);
            }
            model.addAttribute("p", p);
            model.addAttribute("nextPage", page+1);
            model.addAttribute("showNext", p.hasNext());
            model.addAttribute("category", category);
            return "Home";
        }
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
