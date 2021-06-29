package com.tianlin.booking.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
//accountId,departureLocation,arrivalLocation,comment,travelDate,endDate
@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trip {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer accountId;

    private String departureLocation;

    private String arrivalLocation;

    private String comment="";

    private Date travelDate;

    private Date endDate;

    @OneToMany(mappedBy="trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Ticket> tickets = new ArrayList<>();

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;


}

