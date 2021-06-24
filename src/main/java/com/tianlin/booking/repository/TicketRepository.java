package com.tianlin.booking.repository;

import com.tianlin.booking.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    List<Ticket> findAllByAccountId(Integer id);

}
