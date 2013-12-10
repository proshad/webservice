package com.generic.service;

import com.generic.dao.BookingDao;
import com.generic.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Proshad
 * Date: 12/9/13
 */
@Service
public class BookingServiceImpl implements BookingService{
    private BookingDao bookingDao;

    @Autowired
    public void setBookingDao(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public void saveOrUpdate(Booking booking) {
        bookingDao.saveOrUpdate(booking);
    }

    @Override
    public void removeBooking(int bookingID) {
        bookingDao.removeBooking(bookingID);
    }

    @Override
    public Booking detailsOfBooking(int bookingID) {
        return bookingDao.detailsOfBooking(bookingID);
    }
}
