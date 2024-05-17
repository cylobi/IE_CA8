package org.ie.mizdooni.dao;

import org.ie.mizdooni.model.UserModel;
import org.hibernate.Session;

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
