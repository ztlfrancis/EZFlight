package com.tianlin.booking;

import com.tianlin.booking.entity.Passenger;
import com.tianlin.booking.service.PassengerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PassTest {
    @Autowired
    PassengerService passengerService;

    @Test
    public void createpass(){
        Passenger passenger = new Passenger();
        passengerService.CreatePassenger("fist","last","email",1);
    }
    @Test
    public void getByAId(){
        passengerService.getPassengerByAccountId(1);
    }
    @Test
    public void getById(){
        passengerService.getPassengerById(1);
    }
}
