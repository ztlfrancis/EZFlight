package com.tianlin.booking.entity;

import java.util.List;

public class TravelHistroy {
    private List<Trip> trips;
    private List<List<Passenger>> passengers;

    public TravelHistroy() {
    }

    public TravelHistroy(List<Trip> trips, List<List<Passenger>> passengers) {
        this.trips = trips;
        this.passengers = passengers;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<List<Passenger>> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<List<Passenger>> passengers) {
        this.passengers = passengers;
    }
}
