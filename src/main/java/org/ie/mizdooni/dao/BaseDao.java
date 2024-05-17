package org.ie.mizdooni.dao;

import com.google.common.base.Preconditions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

class SessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static Configuration configuration;

    private SessionFactoryUtil() {
    }

    public static Configuration getConfiguration() {
        if (configuration == null) {
            configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
        }
        return configuration;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            var conf = getConfiguration();
            sessionFactory = conf.buildSessionFactory();
        }
        return sessionFactory;
    }
}

public abstract class BaseDao<T> {

    private Class<T> entityClass;

    public BaseDao(final Class<T> entityClass) {
        setEntityClass(entityClass);
    }

    protected void setEntityClass(final Class<T> entityClass) {
        this.entityClass = Preconditions.checkNotNull(entityClass);
    }

    protected Session getCurrentSession() {
        return SessionFactoryUtil.getSessionFactory().getCurrentSession();
    }

    public T findOne(final Long id) {
        T result;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            result = session.get(entityClass, id);
        }
        return result;
    }

    public List<T> findAll() {
        SessionFactoryUtil.getConfiguration().addAnnotatedClass(entityClass);
        List<T> result;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            result = session.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
            session.getTransaction().commit();
        }
        return result;
    }

    public void create(final T entity) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void update(final T entity) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(final T entity) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        if (entity != null) {
            delete(entity);
        }
    }

    // singleton
    protected static BaseDao instance = null;
}
