package com.tianlin.booking.service;

import com.tianlin.booking.entity.Ticket;
import com.tianlin.booking.entity.Trip;
import com.tianlin.booking.repository.TicketRepository;
import com.tianlin.booking.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
//accountId,departureLocation,arrivalLocation,comment,travelDate,endDate
public class TripServiceImpl implements TripService{

    @Autowired
    TripRepository tripRepository;

    public Trip CreateTrip(int accountId, String departureLocation, String arrivalLocation, String comment, Date travelDate, Date endDate){
        Trip trip = new Trip();
        trip.setAccountId(accountId);
        trip.setDepartureLocation(departureLocation);
        trip.setArrivalLocation(arrivalLocation);
        trip.setComment(comment);
        trip.setTravelDate(travelDate);
        trip.setEndDate(endDate);
        return tripRepository.save(trip);
    }

    public void DeleteTrip(int tripId){
        tripRepository.deleteById(tripId);
    }

    public List<Trip> GetTripByAccountId(int accountId) {
        return tripRepository.findAllByAccountIdOrderByTravelDate(accountId);
    }
    public void UpdateComment(String comment,Integer tripId) {

        Trip trip = tripRepository.findById(tripId).get();
        trip.setComment(comment);
        tripRepository.save(trip);
    }
}
