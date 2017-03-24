package com.novauc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Merlin on 3/23/17.
 */
@Controller
public class PurchasesController {
//    @RequestMapping(path = "/", method = RequestMethod.GET)
//    public String home(Model model, String category, Integer page) {
//        page = (page == null) ? 0 : page;
//        PageRequest pr = new PageRequest(page, 10);
//        Page<Purchase> p;
//        if (category != null) {
//            p = purchases.findByCategory(pr, category);
//        }
//        else {
//            p = purchases.findAll(pr);
//        }
//        model.addAttribute("purchases", p);
//        model.addAttribute("nextPage", page+1);
//        model.addAttribute("showNext", p.hasNext());
//        return "home";
//    }
}
