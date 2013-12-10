package com.generic.service;

import com.generic.dao.ProductImageDao;
import com.generic.dao.ProductRateDao;
import com.generic.entity.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Proshad
 * Date: 12/5/13
 */
@Service
public class ProductImageServiceImpl implements ProductImageService{
    private ProductImageDao productImageDao;

    @Autowired
    public void setProductImageDao(ProductImageDao productImageDao) {
        this.productImageDao = productImageDao;
    }

    @Override
    public List<ProductImage> getAllImagesOfAProduct(int productID) {
        return productImageDao.getAllImagesOfAProduct(productID);
    }

    @Override
    public void saveOrUpdate(ProductImage productImage) {
        productImageDao.saveOrUpdate(productImage);
    }

    @Override
    public void removeImageOfAProduct(int productImageID) {
        productImageDao.removeImageOfAProduct(productImageID);
    }
}
