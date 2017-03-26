package com.novauc;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Controller
public class HibernateController {

    @Autowired
    PurchaseRepo purchases;
    @Autowired
    CustomerRepo customers;

    //I TRIED TO USE A SORT OBJECT BUT IT WAS NOT PROPERLY FUNCTIONING WITH THE PAGEABLE PARAMETERS
//    public Sort sortAscBy(String sorter){
//        return new Sort(Sort.Direction.ASC, sorter);
//    }
//
//    public Sort sortDescBy(String sorter){
//        return new Sort(Sort.Direction.DESC, sorter);
//    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String sorter,Integer page) {
        page = (page == null) ? 0 : page;
        //THIS IS THE LINE I WILL USE FOR NOW
        PageRequest pr = new PageRequest(page, 5);
        Page<Purchase> p = purchases.findAll(pr);

        if(sorter!=null){
            if (sorter.equalsIgnoreCase("all")) {
                p = purchases.findAll(pr);
                model.addAttribute("purchases", p);
            } else if (!sorter.equalsIgnoreCase("all")) {
                if (sorter.equalsIgnoreCase("asc")){
                    pr = new PageRequest(page,5, Sort.Direction.ASC,"date");
                    p = purchases.findAll(pr);
                }
                else if (sorter.equalsIgnoreCase("desc")){
                    pr = new PageRequest(page,5, Sort.Direction.DESC,"date");

                    p = purchases.findAll(pr);
                }
                else{
                    p=purchases.findAll(pr);
                }
                model.addAttribute("purchases", p);
            }

        }
        model.addAttribute("sorter", sorter);
        model.addAttribute("purchases", p);
        model.addAttribute("previousPage", page-1);
        model.addAttribute("nextPage", page+1);
        model.addAttribute("showNext", p.hasNext());
        model.addAttribute("showPrevious",p.hasPrevious());
        model.addAttribute("pageNumber",page);
        return "home";
    }

    @PostConstruct
    void enterPurchases() throws IOException,ParseException {
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
//        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
//        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        if(purchases.count()==0) {
            while (purchaseReader.hasNext()) {
                String lineP = purchaseReader.nextLine();
                String[] attributes = lineP.split(",");
                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(attributes[1]);
                Date sqlDate = new java.sql.Date(utilDate.getTime());
//                Date entered = Date.from(formatter.parse(attributes[1]));
                System.out.println(sqlDate);
                Purchase purchase = new Purchase(customers.findFirstById(Integer.valueOf(attributes[0])), sqlDate, attributes[2],Integer.valueOf(attributes[3]),attributes[4]);
                //System.out.println(LocalDateTime.from(f.parse(attributes[1])));
                purchases.save(purchase);
            }
        }

    }

}
