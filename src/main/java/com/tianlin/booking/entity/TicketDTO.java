package com.tianlin.booking.entity;

import lombok.Data;

import java.util.Date;
@Data
public class TicketDTO {
    private Integer id;

    private Integer accountId;

    private Integer passengerId;

    private Integer totalPrice;

    private String departureLocation;

    private String arrivalLocation;

    private Date travelDate;

    private Date endDate;

    private String pass_firstname;

    private String pass_lastname;
}
