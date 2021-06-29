package com.tianlin.booking.repository;

import com.tianlin.booking.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {


    List<Ticket> findAllByTripId(Integer id);
    @Transactional
    void deleteTicketByPassengerIdAndAndTripId(Integer passengerId, Integer tripId);


}
