package com.tianlin.booking.Repository;

import com.tianlin.booking.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByAccountId(Integer id);

}
