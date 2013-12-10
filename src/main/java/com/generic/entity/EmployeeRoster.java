package com.generic.entity;

import javax.persistence.*;

/**
 * User: Proshad
 * Date: 12/9/13
 */
@Entity
public class EmployeeRoster {
    @Id
    @GeneratedValue
    private int employeeRosterID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employeeID")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rosterID")
    private Roster roster;

    public EmployeeRoster() {
    }

    public int getEmployeeRosterID() {
        return employeeRosterID;
    }

    public void setEmployeeRosterID(int employeeRosterID) {
        this.employeeRosterID = employeeRosterID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }
}
