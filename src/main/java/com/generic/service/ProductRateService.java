package com.generic.service;

import com.generic.entity.ProductRate;
import com.generic.entity.Rate;

import java.util.List;

/**
 * User: proshad
 * Date: 9/22/13
 */
public interface ProductRateService {
    public List<Rate> getAllRatesOfAProduct(int productID);

    public void saveOrUpdate(ProductRate productRate);

    public void removeRateOfAProduct(int productID);
}
