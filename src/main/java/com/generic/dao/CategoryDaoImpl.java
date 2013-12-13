package com.generic.dao;

import com.generic.entity.Category;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: proshad
 * Date: 8/19/13
 */

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(CategoryDaoImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> listOfCategory() {
        Session session = sessionFactory.getCurrentSession();
//        String q = "from Category";
//        Query query = session.createQuery(q);
//        return query.list();
        return session.createQuery("from Category c where c.parentCatID = :parentCatID").setInteger("parentCatID", 0).list();
    }

    @Override
    public List<Category> listOfSubCategory(int parentCatID) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Category c where c.parentCatID = :parentCatID").setInteger("parentCatID", parentCatID).list();
    }

    @Override
    public void saveOrUpdate(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
        session.flush();
    }

    @Override
    public void removeCategory(int catID) {
        Session session = sessionFactory.getCurrentSession();
        Category category = (Category) session.load(Category.class, catID);
        if (null != category) {
            session.delete(category);
            session.flush();
            session.close();
        }
    }


    @Override
    public Category detailsOfCategory(int catID) {
        Session session = sessionFactory.getCurrentSession();
        Category category = (Category) session.load(Category.class, catID);
        return category;
    }
}
