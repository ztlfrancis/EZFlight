package com.tianlin.booking.repository;

import com.tianlin.booking.Entity.Passenger;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PassengerRepository extends CrudRepository<Passenger, Integer> {
    List<Passenger> findAllByAccountId(Integer id);
}
