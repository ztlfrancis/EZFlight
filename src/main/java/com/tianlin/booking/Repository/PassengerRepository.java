package com.tianlin.booking.Repository;

import com.tianlin.booking.entity.Passenger;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PassengerRepository extends CrudRepository<Passenger, Integer> {
    List<Passenger> findAllByAccountId(Integer id);
}
