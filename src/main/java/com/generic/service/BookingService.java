package com.generic.service;

import com.generic.entity.Booking;

/**
 * User: Proshad
 * Date: 12/9/13
 */
public interface BookingService {
    public void saveOrUpdate(Booking booking);
    public void removeBooking(int bookingID);
    public Booking detailsOfBooking(int bookingID);
}
