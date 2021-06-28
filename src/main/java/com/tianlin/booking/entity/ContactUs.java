package com.tianlin.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactUs {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "{NotEmpty.ContactUs.EmailError}")
    @Email(message = "{Email.ContactUs.EmailError}")
    private String email;

    @NotEmpty(message = "{NotEmpty.ContactUs.FullName}")
    @Size(min = 3, message = "{Size.ContactUs.FullName}")
    private String fullName;

    @NotEmpty(message = "{NotEmpty.ContactUs.Content}")
    @Size(min = 10, max = 4000, message = "{Size.ContactUs.Content}")
    private String content;

    @Column(nullable = false, insertable=false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
