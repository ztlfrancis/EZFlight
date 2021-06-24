package com.tianlin.booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping(value = "/home")
    public ModelAndView mainPage(){
       return new ModelAndView("index");
    }

    @GetMapping(value = "/login")
    public ModelAndView loginPage(){
        return new ModelAndView("login");
    }

    @GetMapping(value = "/signup")
    public ModelAndView registerPage(){
        return new ModelAndView("register");
    }

    @GetMapping(value = "/addpass")
    public ModelAndView addPassPage(){
        return new ModelAndView("addPass");
    }


    @GetMapping(value = "/showticket")
    public ModelAndView allTicketPage(){
        return new ModelAndView("ticket");
    }

    @GetMapping(value = "/billing")
    public ModelAndView allBillingPage(){
        return new ModelAndView("billing");
    }


}
