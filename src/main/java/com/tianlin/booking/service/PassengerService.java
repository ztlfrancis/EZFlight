package com.tianlin.booking.service;

import com.tianlin.booking.entity.Passenger;

public interface PassengerService {
    public Passenger CreatePassenger(String firstName, String lastName, String email, int accountId);
}
