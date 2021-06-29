package com.tianlin.booking.service;

import com.tianlin.booking.entity.Ticket;
import com.tianlin.booking.entity.Trip;
import com.tianlin.booking.repository.TicketRepository;
import com.tianlin.booking.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
//accountId,departureLocation,arrivalLocation,comment,travelDate,endDate
public class TripServiceImpl implements TripService{

    @Autowired
    TripRepository tripRepository;
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    private TicketServiceImpl ticketService;

    public Trip createTrip(int accountId, String departureLocation, String arrivalLocation, String comment, Date travelDate, Date endDate, String[] passengers){
        Trip trip = new Trip();
        List<Ticket> tickets = trip.getTickets();
        trip.setAccountId(accountId);
        trip.setDepartureLocation(departureLocation);
        trip.setArrivalLocation(arrivalLocation);
        trip.setComment(comment);
        trip.setTravelDate(travelDate);
        trip.setEndDate(endDate);
        trip.setTickets(tickets);
        for(String passengerId:passengers){
            System.out.println(passengerId);
            Ticket ticket = ticketService.CreateTicket(0,Integer.parseInt(passengerId));
            ticket.setTrip(trip);
            tickets.add(ticket);
        }
        return tripRepository.save(trip);
    }

    public void DeleteTrip(int tripId){
        tripRepository.deleteById(tripId);
    }

    public List<Trip> GetTripByAccountId(int accountId) {
        List<Trip> tripByAccount = tripRepository.findAllByAccountIdOrderByTravelDate(accountId);
        List<Trip> ans  = new ArrayList<>();
        for(Trip t:tripByAccount){
            if(ticketRepository.findAllByTripId(t.getId()).size()!=0){
               ans.add(t);
            }
        }

        return ans;
    }
    public void UpdateComment(String comment,Integer tripId) {

        Trip trip = tripRepository.findById(tripId).get();
        trip.setComment(comment);
        tripRepository.save(trip);
    }
}
