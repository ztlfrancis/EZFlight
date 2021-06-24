package com.tianlin.booking.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;



@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer accountId;

    private Integer passengerId;

    private Integer totalPrice;

    private String departureLocation;

    private String arrivalLocation;

    private Date travelDate;

    private Date endDate;

    @Column(nullable = false, insertable=false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(nullable = false, insertable=false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


}

