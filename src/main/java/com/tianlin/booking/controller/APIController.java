package com.tianlin.booking.controller;


import com.tianlin.booking.entity.*;

import com.tianlin.booking.repository.AccountRepository;
import com.tianlin.booking.repository.PassengerRepository;
import com.tianlin.booking.repository.TicketRepository;
import com.tianlin.booking.exceptions.ResourceNotFoundException;
import com.tianlin.booking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


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
    private TicketServiceImpl ticketService;

    @Autowired
    private TripServiceImpl tripService;

    @Autowired
    private  AccountRepository accountRepository;

    @GetMapping(path="/accounts/tickets")
    @ResponseBody
    public TravelHistroy getTicket(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie accountId = null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("accountId"))
                accountId = cookie;
        }
        if(accountId== null)
            return new TravelHistroy();
        TravelHistroy travelHistroy = new TravelHistroy();
        List<Trip> trips = tripService.GetTripByAccountId(Integer.parseInt(accountId.getValue()));
        List<Passenger> allpass = passengerService.getPassengerByAccountId(Integer.parseInt(accountId.getValue()));
        HashMap<Integer,Passenger> passengerHashMap = new HashMap<>();
        for(Passenger pass:allpass ){
            passengerHashMap.put(pass.getId(),pass);
        }

        List<List<Passenger>> passlist = new ArrayList<>();
        travelHistroy.setTrips(trips);
        for(Trip trip:trips){
            List<Passenger> passengers =  new ArrayList<>();
            List<Ticket> tickets = ticketService.GetTicketsByTripId(trip.getId());
            for(Ticket ticket:tickets){
                passengers.add(passengerHashMap.get(ticket.getPassengerId()));
            }
            passlist.add(passengers);
        }
        travelHistroy.setPassengers(passlist);

        return travelHistroy;
    }

    @PostMapping(path="/accounts/trips")
    @ResponseBody
    public void createTrip(@RequestBody Map<String,String> params ,HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie accountId = null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("accountId"))
                accountId = cookie;
        }
        if(accountId== null)
            return;
        if(params.get("tripId")!=null){
            int tripId = Integer.parseInt((String) params.get("tripId"));
            if(params.get("passengerId")!=null)
            ticketService.CreateTicket(tripId,Integer.parseInt((String)params.get("passengerId")));
            return;
        }
        String[] passengers1 = params.get("passengerId").split("&");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try{
            Trip tempTrip = tripService.CreateTrip(
                    Integer.parseInt(accountId.getValue()),
                    (String)params.get("FromPlace"),
                    (String)params.get("ToPlace"),
                    "",
                     formatter.parse(params.get("DepartureDate")),
                    formatter.parse(params.get("ReturnDate")));
            for(String passengerId:passengers1){
                System.out.println(passengerId);
                ticketService.CreateTicket(tempTrip.getId(),Integer.parseInt(passengerId));
            }
        }catch (Exception e){

        }

    }

    @PutMapping(path="/accounts/{id}/tickets/{ticketId}")
    @ResponseBody
    public Ticket updateTicket(@RequestBody Ticket ticket, @PathVariable(value = "id") Integer accountId, @PathVariable(value = "ticketId") Integer ticketId) {
        ticket.setId(ticketId);
        return ticketRepository.save(ticket);
    }

    @DeleteMapping(path="/accounts/tickets/{passengerId}/{tripId}")
    @ResponseBody
    public void deleteTicket( @PathVariable(value = "passengerId") Integer passengerId,@PathVariable(value = "tripId") Integer tripId,HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie accountId = null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("accountId"))
                accountId = cookie;
        }
        if(accountId== null)
            return;
        ticketService.DeleteTicketByPassengerIdAndTripId(tripId,passengerId);
        System.out.println(passengerId+" "+tripId);
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
    @PostMapping(path="/test")
    @ResponseBody
    public void testTrip(@RequestBody Map<String,String> params ,HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie accountId = null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("accountId"))
                accountId = cookie;
        }

        System.out.println((String) params.get("FromPlace")+" "+(String) params.get("ToPlace")+" "+accountId.getValue());
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            System.out.println(accountId.getValue() + " " + (String) params.get("FromPlace") + " " + (String) params.get("ToPlace") + " " + formatter.parse((String) params.get("DepartureDate")).toString() + " " + formatter.parse((String) params.get("ReturnDate")).toString()+" "+(String)params.get("passengerId"));


        }catch (Exception e){

        }
    }

    @PutMapping(path="/trip/{tripId}/{comment}")
    @ResponseBody
    public void updateComment( @PathVariable(value = "tripId") Integer tripId,@PathVariable(value = "comment") String comment,HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie accountId = null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("accountId"))
                accountId = cookie;
        }
        if(accountId== null)
            return;
        System.out.println(tripId+"  "+comment);
        tripService.UpdateComment(comment,tripId);
    }

}
