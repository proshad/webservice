package com.generic.dao;

import com.generic.entity.Roster;
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
public class RosterDaoImpl implements RosterDao{
    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(RosterDaoImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Roster> listOfRoster() {
        Session session = sessionFactory.getCurrentSession();
        String q = "from Roster";
        Query query = session.createQuery(q);
        return query.list();
    }

    @Override
    public void saveOrUpdate(Roster roster) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(roster);
        session.flush();
    }

    @Override
    public void removeRoster(int rosterID) {
        Session session = sessionFactory.getCurrentSession();
        Roster roster = (Roster) session.load(Roster.class, rosterID);
        if (null != roster) {
            session.delete(roster);
            session.flush();
            session.close();
        }
    }

    @Override
    public Roster detailsOfRoster(int rosterID) {
        Session session = sessionFactory.getCurrentSession();
        Roster roster = (Roster) session.load(Roster.class, rosterID);
        return roster;
    }
}
