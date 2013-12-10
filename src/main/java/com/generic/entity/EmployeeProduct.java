package com.generic.entity;

import javax.persistence.*;

/**
 * User: proshad
 * Date: 11/17/13
 */
@Entity
public class EmployeeProduct {
    @Id
    @GeneratedValue
    private int empProductID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employeeID")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productID")
    private Product product;

    public EmployeeProduct() {

    }

    public int getEmpProductID() {
        return empProductID;
    }

    public void setEmpProductID(int empProductID) {
        this.empProductID = empProductID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
