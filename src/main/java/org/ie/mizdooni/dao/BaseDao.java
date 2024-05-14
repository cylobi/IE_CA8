package org.ie.mizdooni.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BaseDao {
    Configuration configuration;
    SessionFactory sessionFactory;
    Session session;

    public BaseDao() {
        configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
    }

    public void startSession() {

        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();

    }

    public void endSession() {
        session.close();
    }

    // public void saveRecord();

}
