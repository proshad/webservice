package com.generic.dao;

import com.generic.entity.EmployeeRoster;
import com.generic.entity.Roster;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Proshad
 * Date: 12/9/13
 */
@Repository
public class EmployeeRosterDaoImpl implements EmployeeRosterDao {
    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(EmployeeRosterDaoImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Roster> getAllRosterOfAEmployee(int employeeID) {
       return sessionFactory.getCurrentSession().createQuery("from EmployeeRoster where employeeID = :employeeID").setInteger("employeeID", employeeID).list();
    }

    @Override
    public void saveOrUpdate(EmployeeRoster employeeRoster) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employeeRoster);
        session.flush();
    }

    @Override
    public void removeRosterOfAEmployee(int employeeID) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete EmployeeRoster where employeeID = :employeeID");
        query.setParameter("employeeID", employeeID);
        int result = query.executeUpdate(); // this will return how many rows have been deleted;
    }
}
