package org.ie.mizdooni.dao;

import org.ie.mizdooni.model.UserModel;

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
}
