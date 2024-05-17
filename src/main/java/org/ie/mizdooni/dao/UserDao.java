package org.ie.mizdooni.dao;

import org.ie.mizdooni.model.UserModel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class UserDao extends BaseDao<UserModel> {
    public UserDao() {
        super(UserModel.class);
    }

    private static class SingletonHelper {
        private static final UserDao INSTANCE = new UserDao();
    }

    public static UserDao getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public boolean isEmailAllowed(String email) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserModel> cr = cb.createQuery(UserModel.class);
            Root<UserModel> root = cr.from(UserModel.class);
            cr.select(root).where(cb.equal(root.get("email"), email));

            Query<UserModel> query = session.createQuery(cr);
            return query.getResultList().isEmpty();
        }
    }

    public UserModel findOneByUsername(String target) {
        UserModel result;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            result = session.get(UserModel.class, target);
        }
        return result;
    }

    public UserModel findUserByUserPass(String username, String password) {
        var userModel = this.findOneByUsername(username);
        if (userModel == null) {
            return null;
        }
        if (!userModel.getPassword().equals(password)) {
            return null;
        }

        return userModel;
    }
}
