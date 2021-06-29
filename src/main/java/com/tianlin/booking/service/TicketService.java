package com.tianlin.booking.service;

import com.tianlin.booking.entity.Passenger;
import com.tianlin.booking.entity.Ticket;

public interface TicketService {
    public Ticket CreateTicket(int tripId, int passengerId);

    public void DeleteTicket(int ticketId);
}
