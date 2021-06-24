package com.tianlin.booking.controller;


import com.tianlin.booking.Entity.Ticket;
import com.tianlin.booking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @RequestMapping(value = "/api")
    public Customer apiTest(){
        return new Customer("Tianlin","123456");
    }
}
