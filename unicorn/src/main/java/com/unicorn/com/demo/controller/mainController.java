package com.unicorn.com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class mainController {
    @RequestMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("index");
    }
    @RequestMapping("/data")
    public ModelAndView dataflow() {
        return new ModelAndView("dataflow");
    }
    @RequestMapping("/side")
    public ModelAndView side() {
        return new ModelAndView("sidebar");
    }
    @RequestMapping("/header")
    public ModelAndView header() {
        return new ModelAndView("header");
    }
}
