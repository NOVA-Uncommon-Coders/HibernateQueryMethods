package com.novauc.controllers;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

/**
 * Created by ANVIL_OCTOPUS on 3/16/17.
 */

//@org.springframework.stereotype.Controller
//public class Controller {
//
//
//    @Autowired
//    CustomerRepository customers;
//
//    @Autowired
//    PurchaseRepository purchases;
//
//
//    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
//    public String addGame(HttpSession session, String gameName, String gamePlatform, String gameGenre, int gameYear) {
//        String userName = (String) session.getAttribute("userName");
//        Purchase purchase = purchases.findFirstByPurchases;
//        Game game = new Game(gameName, gamePlatform, gameGenre, gameYear, user);
//        games.save(game);
//        return "redirect:/";
//
//    }
//
//    @PostConstruct
//    public void init() {
//        if (users.count() == 0) {
//            BIConversion.User user = new BIConversion.User();
//            user.name = "Zach";
//            user.password = "hunter2";
//            users.save(user);
//        }
//    }
//}