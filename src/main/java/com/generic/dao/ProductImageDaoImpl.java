package com.generic.dao;

import com.generic.entity.ProductImage;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Proshad
 * Date: 12/5/13
 */
@Repository
public class ProductImageDaoImpl implements ProductImageDao{
    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(ProductImageDaoImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<ProductImage> getAllImagesOfAProduct(int productID) {
        return sessionFactory.getCurrentSession().createQuery("from ProductImage where productID = :productID").setInteger("productID", productID).list();
    }

    @Override
    public void saveOrUpdate(ProductImage productImage) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(productImage);
        session.flush();
    }

    @Override
    public void removeImageOfAProduct(int productImageID) {
        Session session = sessionFactory.getCurrentSession();
        ProductImage productImage = (ProductImage) session.load(ProductImage.class, productImageID);
        if (null != productImage) {
            session.delete(productImage);
            session.flush();
            session.close();
        }
    }
}
