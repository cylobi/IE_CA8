package org.ie.mizdooni.dao;

import org.ie.mizdooni.model.ClientUserModel;

public class ClientUserDao extends BaseDao<ClientUserModel> {
    public ClientUserDao() {
        super(ClientUserModel.class);
    }

    private static class SingletonHelper {
        private static final ClientUserDao INSTANCE = new ClientUserDao();
    }

    public static ClientUserDao getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
