package com.tianlin.booking.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String address;
    private Date createAt;
    private Date updateAt;
    @Type(type="true_false")
    private boolean exist;
}
