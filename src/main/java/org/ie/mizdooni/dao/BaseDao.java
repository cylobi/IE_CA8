package org.ie.mizdooni.dao;

import com.google.common.base.Preconditions;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

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

    public List<T> findByCriteria(List<String> selectedColumns, Map<String, Object> criteria) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> root = cq.from(entityClass);
            if (criteria != null) {
                for (Map.Entry<String, Object> entry : criteria.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();

                    Predicate predicate;
                    if (value instanceof Number) {
                        predicate = cb.equal(root.get(key), value);
                    } else if (value instanceof String) {
                        predicate = cb.like(root.get(key), "%" + value + "%");
                    } else if (value instanceof Boolean) {
                        predicate = cb.equal(root.get(key), value);
                    } else {
                        throw new IllegalArgumentException("Unsupported type for criteria");
                    }
                    cq.where(predicate);
                }
            }
            if (selectedColumns != null) {
                Expression<?>[] selections = selectedColumns.stream()
                        .map(root::get)
                        .toArray(Expression[]::new);
                cq.multiselect(selections);
            }

            Query<T> query = session.createQuery(cq);
            return query.getResultList();
        }
    }


    // singleton
    protected static BaseDao instance = null;
}
