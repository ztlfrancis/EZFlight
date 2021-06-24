package com.tianlin.booking.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer accountId;

    private Integer totalPrice;

    private String departureLocation;

    private String arrivalLocation;

    private Date travelDate;

    private Date endDate;

    private Date transactionDate;