package com.generic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.PrivateKey;

/**
 * User: Proshad
 * Date: 12/5/13
 */
@Entity
public class Roster {
    @Id
    @GeneratedValue
    private int rosterID;
    @Column(length = 500)
    private String name;
    @Column(length = 100)
    private String day;
    @Column(length = 50)
    private String startTime;
    @Column(length = 50)
    private String endTime;

    public Roster() {
    }

    public Roster(String name, String day, String startTime, String endTime) {
        this.name = name;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getRosterID() {
        return rosterID;
    }

    public void setRosterID(int rosterID) {
        this.rosterID = rosterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
