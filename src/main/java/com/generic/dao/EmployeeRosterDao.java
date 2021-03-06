package com.generic.dao;

import com.generic.entity.EmployeeRoster;
import com.generic.entity.Roster;

import java.util.List;

/**
 * User: Proshad
 * Date: 12/9/13
 */
public interface EmployeeRosterDao {
    public List<Roster> getAllRosterOfAEmployee(int employeeID);

    public void saveOrUpdate(EmployeeRoster employeeRoster);

    public void removeRosterOfAEmployee(int employeeID);
}
