package com.tianlin.booking.controller;


import com.tianlin.booking.entity.Passenger;
import com.tianlin.booking.entity.Ticket;

import com.tianlin.booking.repository.PassengerRepository;
import com.tianlin.booking.repository.TicketRepository;
import com.tianlin.booking.exceptions.ResourceNotFoundException;
import com.tianlin.booking.service.PassengerService;
import com.tianlin.booking.service.PassengerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1" )
public class APIController {


    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerServiceImpl passengerService;

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

    @GetMapping(path="/accounts/{id}/passenger")
    @ResponseBody
    public List<Passenger> getPassenger(@PathVariable(value = "id") Integer accountId) {
        return passengerRepository.findAllByAccountId(accountId);
    }
    @PostMapping(path="/accounts/{id}/passenger")
    @ResponseBody
    public Passenger createPassenger(@RequestBody Map<String,String> params, @PathVariable(value = "id") Integer accountId) {
        System.out.println("firstName: "+params.get("firstName")+" lastName: "+params.get("lastName")+" email: "+params.get("email"));
        return passengerService.CreatePassenger(
                params.get("firstName"),
                params.get("lastName"),
                params.get("email"),
                accountId);
    }

    @PutMapping(path="/accounts/{id}/passenger/{passengerId}")
    @ResponseBody
    public Passenger updatePassenger(@RequestBody Passenger passenger, @PathVariable(value = "id") Integer accountId, @PathVariable(value = "passengerId") Integer passengerId) {
        passenger.setAccountId(accountId);
        passenger.setId(passengerId);
        return passengerRepository.save(passenger);
    }

    @DeleteMapping(path="/accounts/{id}/passenger/{passengerId}")
    @ResponseBody
    public ResponseEntity<?> deletePassenger(@PathVariable(value = "id") Integer accountId, @PathVariable(value = "passengerId") Integer passengerId) {
        Passenger existingPassenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger", "id", passengerId));
        passengerRepository.delete(existingPassenger);
        return ResponseEntity.ok().build();

    }


}
