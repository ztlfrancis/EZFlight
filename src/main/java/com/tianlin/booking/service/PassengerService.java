package com.tianlin.booking.service;

import com.tianlin.booking.entity.Passenger;

import java.util.List;

public interface PassengerService {
    public Passenger CreatePassenger(String firstName, String lastName, String email, int accountId);

    Passenger getPassengerById(Integer id);
    List<Passenger> getPassengerByAccountId(Integer accountId);
}
