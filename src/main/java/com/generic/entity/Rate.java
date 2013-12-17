package com.generic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User: Proshad
 * Date: 12/5/13
 */
@Entity
public class Rate {
    @Id
    @GeneratedValue
    private int rateID;
    private String rateName;
    @Column(length = 1000)
    private String rateDescription;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean status = true;
    private double price = 0.0;

    public Rate() {
    }

    public Rate(String rateName, String rateDescription, boolean status, float price) {
        this.rateName = rateName;
        this.rateDescription = rateDescription;
        this.status = status;
        this.price = price;
    }
    public Rate(String rateName, String rateDescription, float price) {
        this.rateName = rateName;
        this.rateDescription = rateDescription;
        this.price = price;
    }

    public int getRateID() {
        return rateID;
    }

    public void setRateID(int rateID) {
        this.rateID = rateID;
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    public String getRateDescription() {
        return rateDescription;
    }

    public void setRateDescription(String rateDescription) {
        this.rateDescription = rateDescription;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
