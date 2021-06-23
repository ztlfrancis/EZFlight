package com.tianlin.booking.controller;

import com.tianlin.booking.Entity.Ticket;
import com.tianlin.booking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @Autowired
    private TicketRepository ticketRepository;



    @GetMapping(path="/api/gettickets")
    public @ResponseBody
    Iterable<Ticket> getTicket() {
        return ticketRepository.findAllByAccountId(1);
    }
}
