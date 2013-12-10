package com.generic.service;

import com.generic.dao.RateDao;
import com.generic.entity.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Proshad
 * Date: 12/5/13
 */
@Service
public class RateServiceImpl implements RateService{
    private RateDao rateDao;

    @Autowired
    public void setRateDao(RateDao rateDao) {
        this.rateDao = rateDao;
    }
    @Override
    public List<Rate> listOfRates() {
        return rateDao.listOfRates();
    }

    @Override
    public void saveOrUpdate(Rate rate) {
        rateDao.saveOrUpdate(rate);
    }

    @Override
    public void removeRate(int rateID) {
        rateDao.removeRate(rateID);
    }

    @Override
    public Rate detailsOfRate(int rateID) {
        return rateDao.detailsOfRate(rateID);
    }
}
