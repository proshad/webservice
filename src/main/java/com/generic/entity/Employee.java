package com.generic.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * User: proshad
 * Date: 11/17/13
 */
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private int employeeID;
    @Column(length = 500)
    private String firstName;
    @Column(length = 500)
    private String lastName;
    @Column(length = 300)
    private String designation;
    @Column(length = 1000)
    private String address;
    @Column(length = 20)
    private String contactNo;
    @Column(length = 300)
    private String imageUrl;
    @Temporal(TemporalType.TIMESTAMP)
    private Date joiningDate;

    @OneToMany(mappedBy="employee",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EmployeeProduct> employeeProducts;


    public Employee(){

    }

    public Employee(String firstName, String lastName, String designation, String address, String contactNo, String imageUrl, Date joiningDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.address = address;
        this.contactNo = contactNo;
        this.imageUrl = imageUrl;
        this.joiningDate = joiningDate;
    }


    public Set<EmployeeProduct> getEmployeeProducts() {
        return employeeProducts;
    }

    public void setEmployeeProducts(Set<EmployeeProduct> employeeProducts) {
        this.employeeProducts = employeeProducts;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }
}
