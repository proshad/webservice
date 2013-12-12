package com.generic.dao;

import com.generic.entity.ProductRate;
import com.generic.entity.Rate;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: proshad
 * Date: 9/22/13
 */
@Repository
public class ProductRateDaoImpl implements ProductRateDao {
    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(ProductRateDaoImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ProductRate> getAllRatesOfAProduct(int productID) {
        return sessionFactory.getCurrentSession().createQuery("from ProductRate where productID = :productID").setInteger("productID", productID).list();
    }

    @Override
    public void saveOrUpdate(ProductRate productRate) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(productRate);
        session.flush();
    }

    @Override
    public void removeRateOfAProduct(int productID) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete ProductRate where productID = :productID");
        query.setParameter("productID", productID);
        int result = query.executeUpdate(); // this will return how many rows have been deleted;
    }

}
