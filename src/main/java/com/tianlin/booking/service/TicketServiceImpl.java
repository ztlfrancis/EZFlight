package com.tianlin.booking.service;

import com.tianlin.booking.entity.Passenger;
import com.tianlin.booking.entity.Ticket;
import com.tianlin.booking.entity.Trip;
import com.tianlin.booking.exceptions.ResourceNotFoundException;
import com.tianlin.booking.repository.PassengerRepository;
import com.tianlin.booking.repository.TicketRepository;
import com.tianlin.booking.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
//tripId,passengerId,price
public class TicketServiceImpl implements TicketService{

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TripServiceImpl tripService;

    public Ticket CreateTicket(int tripId, int passengerId){
        Ticket ticket  =  new Ticket();
        ticket.setTripId(tripId);
        ticket.setPassengerId(passengerId);

        return ticketRepository.save(ticket);
    }
    public void DeleteTicket(int ticketId){
        //add try ecp later
        Ticket curTicket = ticketRepository.findById(ticketId).get();
        int tripId = curTicket.getTripId();
        List<Ticket> tickets = ticketRepository.findAllByTripId(tripId);
        if(tickets.size() == 1) {
            tripService.DeleteTrip(tripId);
        };
        ticketRepository.deleteById(ticketId);

    }
    public List<Ticket> GetTicketsByTripId(Integer tripId){
        return ticketRepository.findAllByTripId(tripId);
    }

    public void DeleteTicketByPassengerIdAndTripId(Integer tripId, Integer passengerId){
        ticketRepository.deleteTicketByPassengerIdAndAndTripId(passengerId,tripId);
    }
}
