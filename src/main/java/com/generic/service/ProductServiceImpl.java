package com.generic.service;

import com.generic.dao.ProductDao;
import com.generic.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: proshad
 * Date: 9/14/13
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> listOfProducts() {
        return productDao.listOfProducts();
    }

    @Override
    public void saveOrUpdate(Product product) {
        productDao.saveOrUpdate(product);
    }

    @Override
    public void removeService(int productID) {
        productDao.removeService(productID);
    }

    @Override
    public Product detailsOfService(int productID) {
        return productDao.detailsOfService(productID);
    }

    @Override
    public List<Product> listOfProductsOfCategory(int categoryID) {
        return productDao.listOfProductsOfCategory(categoryID);
    }
}
