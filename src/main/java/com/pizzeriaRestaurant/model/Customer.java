package com.pizzeriaRestaurant.model;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    private String email;
    private String name;
    private String password;
    private Integer contact;

    private String address;

    public Customer() {
        super();
    }

    public Customer(String email, String name, String password, Integer contact, String address) {
        super();
        this.email = email;
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
