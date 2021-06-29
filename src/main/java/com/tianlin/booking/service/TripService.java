package com.tianlin.booking.service;

import com.tianlin.booking.entity.Trip;

import java.util.Date;

public interface TripService {
//    Trip CreateTrip(int accountId, String departureLocation, String arrivalLocation, String comment, Date travelDate, Date endDate);
    Trip createTrip(int accountId, String departureLocation, String arrivalLocation, String comment, Date travelDate, Date endDate, String[] passengers1);
    void DeleteTrip(int tripId);
    void UpdateComment(String comment,Integer tripId);
}