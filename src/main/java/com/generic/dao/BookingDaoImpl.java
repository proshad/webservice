package com.generic.dao;

import com.generic.entity.Booking;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * User: Proshad
 * Date: 12/9/13
 */
@Repository
public class BookingDaoImpl implements BookingDao{
    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(BookingDaoImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void saveOrUpdate(Booking booking) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(booking);
        session.flush();
    }

    @Override
    public void removeBooking(int bookingID) {
        Session session = sessionFactory.getCurrentSession();
        Booking booking = (Booking) session.load(Booking.class, bookingID);
        if (null != booking) {
            session.delete(booking);
            session.flush();
            session.close();
        }
    }

    @Override
    public Booking detailsOfBooking(int bookingID) {
        Session session = sessionFactory.getCurrentSession();
        Booking booking = (Booking) session.load(Booking.class, bookingID);
        return booking;
    }
}
