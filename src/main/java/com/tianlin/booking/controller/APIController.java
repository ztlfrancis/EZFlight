package com.tianlin.booking.controller;


import com.tianlin.booking.Entity.Ticket;

import com.tianlin.booking.Repository.TicketRepository;
import com.tianlin.booking.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1" )
public class APIController {


    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(path="/accounts/{id}/tickets")
    @ResponseBody
    public List<Ticket> getTicket(@PathVariable(value = "id") Integer accountId) {
        return ticketRepository.findAllByAccountId(accountId);
    }

    @PostMapping(path="/accounts/{id}/tickets")
    public @ResponseBody
    Ticket createTicket(@RequestBody Ticket ticket, @PathVariable(value = "id") Integer accountId) {
        ticket.setAccountId(accountId);
        return ticketRepository.save(ticket);
    }

    @PutMapping(path="/accounts/{id}/tickets/{ticketId}")
    @ResponseBody
    public Ticket updateTicket(@RequestBody Ticket ticket, @PathVariable(value = "id") Integer accountId, @PathVariable(value = "ticketId") Integer ticketId) {
        ticket.setAccountId(accountId);
        ticket.setId(ticketId);
        return ticketRepository.save(ticket);
    }

    @DeleteMapping(path="/accounts/{id}/tickets/{ticketId}")
    @ResponseBody
    public ResponseEntity<?> deleteTicket(@PathVariable(value = "id") Integer accountId, @PathVariable(value = "ticketId") Integer ticketId) {
        Ticket existingTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", ticketId));

        ticketRepository.delete(existingTicket);
        return ResponseEntity.ok().build();

    }
}
