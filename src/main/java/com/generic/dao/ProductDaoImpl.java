package com.generic.dao;

import com.generic.entity.Product;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * User: proshad
 * Date: 9/14/13
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(ProductDaoImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<Product> listOfProducts() {
        Session session = sessionFactory.getCurrentSession();
        String q = "from Product";
        Query query = session.createQuery(q);
        return query.list();
    }

    @Override
    public void saveOrUpdate(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        session.flush();
    }

    @Override
    public void removeService(int productID) {
        Session session = sessionFactory.getCurrentSession();
        Product product = (Product) session.load(Product.class, productID);
        if (null != product) {
            session.delete(product);
            session.flush();
            session.close();
        }
    }

    @Override
    public Product detailsOfService(int productID) {
        Session session = sessionFactory.getCurrentSession();
        Product product = (Product) session.load(Product.class, productID);
        return product;
    }

    @Override
    public List<Product> listOfProductsOfCategory(int categoryID) {
        return sessionFactory.getCurrentSession().createQuery("from Product where categoryID = :categoryID").setInteger("categoryID", categoryID).list();
    }
}
