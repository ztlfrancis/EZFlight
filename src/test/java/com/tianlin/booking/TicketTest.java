package com.tianlin.booking;

import com.tianlin.booking.entity.Ticket;
import com.tianlin.booking.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicketTest {
    @Autowired
    TicketService ticketService;

    @Test
    public void create(){
        Ticket ticket = new Ticket();
        ticketService.CreateTicket(23,1);
    }
    @Test
    public void dele(){
        ticketService.DeleteTicket(1);
    }

}
