package com.tianlin.booking.controller.Repository;

import com.tianlin.booking.Entity.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    List<Ticket> findAllByAccountId(Integer id);
}
