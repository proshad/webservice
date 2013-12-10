package com.generic.service;

import com.generic.dao.ProductRateDao;
import com.generic.entity.ProductRate;
import com.generic.entity.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: proshad
 * Date: 9/22/13
 */
@Service
public class ProductRateServiceImpl implements ProductRateService {
    private ProductRateDao productRateDao;
    @Autowired
    public void setProductRateDao(ProductRateDao productRateDao) {
        this.productRateDao = productRateDao;
    }

    @Override
    public List<Rate> getAllRatesOfAProduct(int productID) {
        return productRateDao.getAllRatesOfAProduct(productID);
    }

    @Override
    public void saveOrUpdate(ProductRate productRate) {
        productRateDao.saveOrUpdate(productRate);
    }

    @Override
    public void removeRateOfAProduct(int productID) {
        productRateDao.removeRateOfAProduct(productID);
    }

}
