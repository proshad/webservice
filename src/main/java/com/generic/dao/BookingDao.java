package com.generic.dao;

import com.generic.entity.Booking;

/**
 * User: Proshad
 * Date: 12/9/13
 */
public interface BookingDao {
    public void saveOrUpdate(Booking booking);
    public void removeBooking(int bookingID);
    public Booking detailsOfBooking(int bookingID);

    //TODO (get all booking of a particular date)

}
