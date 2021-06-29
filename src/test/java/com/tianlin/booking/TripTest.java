package com.tianlin.booking;

import com.tianlin.booking.service.TripService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TripTest {
    @Autowired
    TripService tripService;
    @Test
    public void create(){
        tripService.CreateTrip(1,"dep","arr","comm",new Date(),new Date());
    }
    @Test
    public void dele(){
        tripService.DeleteTrip(23);
    }
    @Test
    public void updatecomm(){
        tripService.UpdateComment("comm11",23);
    }

}
