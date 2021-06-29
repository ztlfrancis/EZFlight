package com.tianlin.booking.service;

import com.tianlin.booking.entity.Passenger;
import com.tianlin.booking.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService{
    @Autowired
    PassengerRepository passengerRepository;

    public Passenger CreatePassenger(String firstName, String lastName, String email, int accountId){
        Passenger passenger = new Passenger();
        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);
        passenger.setEmail(email);
        passenger.setAccountId(accountId);

        return passengerRepository.save(passenger);
    }

    public Passenger getPassengerById(Integer id) {
        return passengerRepository.findById(id).get();
    }
    public List<Passenger> getPassengerByAccountId(Integer accountId) {
        return passengerRepository.findAllByAccountId(accountId);
    }
}
