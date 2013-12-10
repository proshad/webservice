package com.generic.service;

import com.generic.dao.EmployeeRosterDao;
import com.generic.entity.EmployeeRoster;
import com.generic.entity.Roster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Proshad
 * Date: 12/9/13
 */
@Service
public class EmployeeRosterServiceImpl implements EmployeeRosterService {
    private EmployeeRosterDao employeeRosterDao;

    @Autowired
    public void setEmployeeRosterDao(EmployeeRosterDao employeeRosterDao) {
        this.employeeRosterDao = employeeRosterDao;
    }

    @Override
    public List<Roster> getAllRosterOfAEmployee(int employeeID) {
        return employeeRosterDao.getAllRosterOfAEmployee(employeeID);
    }

    @Override
    public void saveOrUpdate(EmployeeRoster employeeRoster) {
        employeeRosterDao.saveOrUpdate(employeeRoster);
    }

    @Override
    public void removeRosterOfAEmployee(int employeeID) {
        employeeRosterDao.removeRosterOfAEmployee(employeeID);
    }
}
