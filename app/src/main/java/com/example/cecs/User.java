package com.example.cecs;

import java.io.Serializable;

/**
 * This class represents a user.
 *
 */
public class User implements Serializable {
    String username, password, email, phoneNumber;

    /*
        User constructor that contains username, password, email, and phoneNumber
     */
    public User(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
