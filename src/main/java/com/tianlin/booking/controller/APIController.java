package com.tianlin.booking.controller;


import com.tianlin.booking.entity.Account;
import com.tianlin.booking.entity.Passenger;
import com.tianlin.booking.entity.Ticket;

import com.tianlin.booking.entity.TicketDTO;
import com.tianlin.booking.repository.AccountRepository;
import com.tianlin.booking.repository.PassengerRepository;
import com.tianlin.booking.repository.TicketRepository;
import com.tianlin.booking.exceptions.ResourceNotFoundException;
import com.tianlin.booking.service.AccountService;
import com.tianlin.booking.service.PassengerService;
import com.tianlin.booking.service.PassengerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1" )
public class APIController {


    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerServiceImpl passengerService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(path="/accounts/tickets")
    @ResponseBody
    public List<TicketDTO> getTicket(HttpServletRequest request) {
        System.out.println("getticket");
        Cookie[] cookies = request.getCookies();
        String id = "";
        for(Cookie c:cookies){
            if(c.getName().equals("id"))
            id = c.getValue();
        }
        List<Ticket> tickets = ticketRepository.findAllByAccountId(Integer.parseInt(id));
        List<TicketDTO> ticketDTOS = new ArrayList<>(tickets.size());
        for(Ticket t:tickets){
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setArrivalLocation(t.getArrivalLocation());
            ticketDTO.setDepartureLocation(t.getArrivalLocation());
            ticketDTO.setTravelDate(t.getTravelDate());
            ticketDTO.setEndDate(t.getEndDate());
            ticketDTO.setTotalPrice(t.getTotalPrice());
            Optional<Passenger> pid = passengerRepository.findById(t.getPassengerId());
            Passenger passenger = pid.get();
            ticketDTO.setPass_firstname(passenger.getFirstName());
            ticketDTO.setPass_lastname(passenger.getLastName());
            ticketDTOS.add(ticketDTO);
        }
        return ticketDTOS;
    }

    @PostMapping(path="/accounts/{id}/tickets")
    @ResponseBody
    public Ticket createTicket(@RequestBody Ticket ticket, @PathVariable(value = "id") Integer accountId) {
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
