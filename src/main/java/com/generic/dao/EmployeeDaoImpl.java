package com.generic.dao;

import com.generic.entity.Employee;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: proshad
 * Date: 11/17/13
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> listOfEmployees() {
        Session session = sessionFactory.getCurrentSession();
        String q = "from Employee";
        Query query = session.createQuery(q);
        return query.list();
    }

    @Override
    public void saveOrUpdate(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
        session.flush();
    }

    @Override
    public void removeEmployee(int employeeID) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = (Employee) session.load(Employee.class, employeeID);
        if (null != employee) {
            session.delete(employee);
            session.flush();
            session.close();
        }
    }

    @Override
    public Employee detailsOfEmployee(int employeeID) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = (Employee) session.load(Employee.class, employeeID);
        return employee;
    }
}
