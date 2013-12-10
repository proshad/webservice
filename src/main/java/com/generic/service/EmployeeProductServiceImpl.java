package com.generic.service;

import com.generic.dao.EmployeeProductDao;
import com.generic.entity.EmployeeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: proshad
 * Date: 11/18/13
 */
@Service
public class EmployeeProductServiceImpl implements EmployeeProductService {
    private EmployeeProductDao employeeProductDao;

    @Autowired
    public void setEmployeeProductDao(EmployeeProductDao employeeProductDao) {
        this.employeeProductDao = employeeProductDao;
    }

    @Override
    public List<EmployeeProduct> listOfProduct(int employeeID) {
        return employeeProductDao.listOfProduct(employeeID);
    }

    @Override
    public void saveOrUpdate(EmployeeProduct employeeProduct) {
        this.employeeProductDao.saveOrUpdate(employeeProduct);
    }
}
