package com.generic.dao;

import com.generic.entity.Rate;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Proshad
 * Date: 12/5/13
 */
@Repository
public class RateDaoImpl implements RateDao {
    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(RateDaoImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Rate> listOfRates() {
        Session session = sessionFactory.getCurrentSession();
        String q = "from Rate";
        Query query = session.createQuery(q);
        return query.list();
    }

    @Override
    public void saveOrUpdate(Rate rate) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(rate);
        session.flush();
    }

    @Override
    public void removeRate(int rateID) {
        Session session = sessionFactory.getCurrentSession();
        Rate rate = (Rate) session.load(Rate.class, rateID);
        if (null != rate) {
            session.delete(rate);
            session.flush();
            session.close();
        }
    }

    @Override
    public Rate detailsOfRate(int rateID) {
        Session session = sessionFactory.getCurrentSession();
        Rate rate = (Rate) session.load(Rate.class, rateID);
        return rate;
    }
}
