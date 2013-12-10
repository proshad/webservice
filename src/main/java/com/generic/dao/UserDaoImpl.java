package com.generic.dao;

import com.generic.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: proshad
 * Date: 12/1/13
 */
@Repository
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> listOfUsers() {
        Session session = sessionFactory.getCurrentSession();
        String q = "from User";
        Query query = session.createQuery(q);
        return query.list();
    }

    @Override
    public void saveOrUpdate(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        session.flush();
    }

    @Override
    public void removeUser(String emailID) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, emailID);
        if (null != user) {
            session.delete(user);
            session.flush();
            session.close();
        }
    }

    @Override
    public User detailsOfUser(String emailID) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, emailID);
        return user;
    }
}
