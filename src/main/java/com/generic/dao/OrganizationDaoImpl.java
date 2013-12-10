package com.generic.dao;

import com.generic.entity.Organization;
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
public class OrganizationDaoImpl implements OrganizationDao {
    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(OrganizationDaoImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Organization> listOfOrganization() {
        Session session = sessionFactory.getCurrentSession();
        String q = "from Organization";
        Query query = session.createQuery(q);
        return query.list();
    }

    @Override
    public void saveOrUpdate(Organization organization) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(organization);
        session.flush();
    }

    @Override
    public void remove(int organizationID) {
        Session session = sessionFactory.getCurrentSession();
        Organization organization = (Organization) session.load(Organization.class, organizationID);
        if (null != organization) {
            session.delete(organization);
            session.flush();
            session.close();
        }
    }

    @Override
    public Organization detailsOfOrganization(int organizationID) {
        Session session = sessionFactory.getCurrentSession();
        Organization organization = (Organization) session.load(Organization.class, organizationID);
        return organization;
    }
}
