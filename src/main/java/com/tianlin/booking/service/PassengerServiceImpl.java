package com.tianlin.booking.service;

import com.tianlin.booking.entity.Passenger;
import com.tianlin.booking.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
@Service
public class PassengerServiceImpl implements PassengerService{
    @Autowired
    Passenger passenger;
    @Autowired
    PassengerRepository passengerRepository;

    public Passenger CreatePassenger(String firstName, String lastName, String email, int accountId){
        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);
        passenger.setEmail(email);
        passenger.setAccountId(accountId);

        return passengerRepository.save(passenger);
    }
}
