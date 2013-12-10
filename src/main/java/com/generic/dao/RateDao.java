package com.generic.dao;

import com.generic.entity.Rate;

import java.util.List;

/**
 * User: Proshad
 * Date: 12/5/13
 */
public interface RateDao {
    public List<Rate> listOfRates();

    public void saveOrUpdate(Rate rate);

    public void removeRate(int rateID);

    public Rate detailsOfRate(int rateID);
}
