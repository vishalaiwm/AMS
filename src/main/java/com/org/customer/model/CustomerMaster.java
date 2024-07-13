package com.org.customer.model;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "customer_master")
public class CustomerMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String street;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String email;
    
    @Column
    private String password;
    
    @Column
    private String confirmPassword;

}
