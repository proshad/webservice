package com.generic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * User: proshad
 * Date: 12/1/13
 */
@Entity
public class User {
    @Id
    private String emailID;
    @Column(length = 500)
    private String password;
    @Column(length = 500)
    private String firstName;
    @Column(length = 500)
    private String lastName;
    @Column(length = 1000)
    private String imageUrl;

    public User() {
    }

    public User(String email, String password, String firstName, String lastName, String imageUrl) {
        this.emailID = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
    }

    public User(String email, String password, String firstName, String lastName) {
        this.emailID = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String email) {
        this.emailID = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
