package com.generic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User: proshad
 * Date: 12/1/13
 */
@Entity
public class Organization {
    @Id
    @GeneratedValue
    private int organizationID;
    @Column(length = 500)
    private String name;
    @Column(length = 5000)
    private String description;
    @Column(length = 200)
    private String tradingHour;
    @Column(length = 1000)
    private String contactDetails;
    @Column(length = 500)
    private String location;
    @Column(length = 500)
    private String tradeLicense;
    private int timeSlotDuration = 30;

    public Organization() {
    }

    public Organization(String name, int timeSlotDuration, String tradeLicense, String location, String contactDetails, String description, String tradingHour) {
        this.name = name;
        this.timeSlotDuration = timeSlotDuration;
        this.tradeLicense = tradeLicense;
        this.location = location;
        this.contactDetails = contactDetails;
        this.description = description;
        this.tradingHour = tradingHour;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTradingHour() {
        return tradingHour;
    }

    public void setTradingHour(String tradingHour) {
        this.tradingHour = tradingHour;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTradeLicense() {
        return tradeLicense;
    }

    public void setTradeLicense(String tradeLicense) {
        this.tradeLicense = tradeLicense;
    }

    public int getTimeSlotDuration() {
        return timeSlotDuration;
    }

    public void setTimeSlotDuration(int timeSlotDuration) {
        this.timeSlotDuration = timeSlotDuration;
    }
}
