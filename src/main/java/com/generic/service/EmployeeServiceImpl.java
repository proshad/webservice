package com.generic.service;

import com.generic.dao.EmployeeDao;
import com.generic.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: proshad
 * Date: 11/17/13
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> listOfEmployees() {
        return employeeDao.listOfEmployees();
    }

    @Override
    public void saveOrUpdate(Employee employee) {
        employeeDao.saveOrUpdate(employee);
    }

    @Override
    public void removeEmployee(int employeeID) {
        employeeDao.removeEmployee(employeeID);
    }

    @Override
    public Employee detailsOfEmployee(int employeeID) {
        return employeeDao.detailsOfEmployee(employeeID);
    }
}
