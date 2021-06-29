package com.tianlin.booking.repository;


import com.tianlin.booking.entity.Trip;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TripRepository extends CrudRepository<Trip, Integer> {
    List<Trip> findAllByAccountId(Integer id);
    List<Trip> findAllByAccountIdOrderByTravelDate(Integer id);

}
