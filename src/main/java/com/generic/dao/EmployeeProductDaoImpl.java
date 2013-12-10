package com.generic.dao;

import com.generic.entity.EmployeeProduct;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: proshad
 * Date: 11/18/13
 */
@Repository
public class EmployeeProductDaoImpl implements EmployeeProductDao {
    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(EmployeeProductDaoImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<EmployeeProduct> listOfProduct(int employeeID) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from EmployeeProduct where employeeID = :employeeID").setInteger("employeeID", employeeID).list();
    }

    @Override
    public void saveOrUpdate(EmployeeProduct employeeProduct) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employeeProduct);
        session.flush();
    }
}
