package com.tianlin.booking.service;

import com.tianlin.booking.entity.Passenger;
import com.tianlin.booking.entity.Trip;

import java.util.Date;

public interface TripService {
    Trip CreateTrip(int accountId, String departureLocation, String arrivalLocation, String comment, Date travelDate, Date endDate);
    void DeleteTrip(int tripId);
    void UpdateComment(String comment,Integer tripId);
}