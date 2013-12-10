package com.test.webservice.util;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

/**
 * User: Proshad
 * Date: 9/10/13
 */
public class HibernateUtil {

    public static <T> T unproxy(T entity) {
        if (entity == null) {
            return null;
        }

        if (entity instanceof HibernateProxy) {
            Hibernate.initialize(entity);
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
        }

        return entity;
    }
}