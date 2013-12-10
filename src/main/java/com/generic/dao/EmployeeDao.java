package com.generic.dao;

import com.generic.entity.Employee;

import java.util.List;

/**
 * User: proshad
 * Date: 11/17/13
 */
public interface EmployeeDao {
    public List<Employee> listOfEmployees();

    public void saveOrUpdate(Employee employee);

    public void removeEmployee(int employeeID);

    public Employee detailsOfEmployee(int employeeID);
}
