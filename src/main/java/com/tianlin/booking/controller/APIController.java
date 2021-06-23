package com.tianlin.booking.controller;

<<<<<<< HEAD
import com.tianlin.booking.Entity.Customer;
import org.springframework.web.bind.annotation.RequestMapping;
=======
import com.tianlin.booking.Entity.Ticket;
import com.tianlin.booking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
>>>>>>> newticket
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @RequestMapping(value = "/api")
    public Customer apiTest(){
        return new Customer("Tianlin","123456");
    }
}
