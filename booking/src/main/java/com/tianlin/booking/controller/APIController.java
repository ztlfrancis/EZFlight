package com.tianlin.booking.controller;

import com.tianlin.booking.Entity.Customer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @RequestMapping(value = "/api")
    public Customer apiTest(){
        return new Customer("Tianlin","123456");
    }
}
