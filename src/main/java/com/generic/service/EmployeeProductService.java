package com.generic.service;

import com.generic.entity.EmployeeProduct;

import java.util.List;

/**
 * User: proshad
 * Date: 11/18/13
 */
public interface EmployeeProductService {
    public List<EmployeeProduct> listOfProduct(int employeeID);
    public void saveOrUpdate(EmployeeProduct employeeProduct);
}
