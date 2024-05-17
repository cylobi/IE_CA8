package org.ie.mizdooni.dao;

import java.util.List;

import org.ie.mizdooni.model.GlobalData;
import org.ie.mizdooni.model.UserModel;
import org.springframework.stereotype.Repository;

public class GlobalDataDao extends BaseDao<GlobalData> {
    private GlobalData data;

    public GlobalDataDao() {
        super(GlobalData.class);
    }

    public GlobalData initializeData() {
        GlobalData newData = new GlobalData();
        newData.setVersion("2.00Beta");
        newData.setId(1);
        this.create(newData);
        return data;
    }

    public GlobalData importOrCreateData() {
        var dataList = this.findAll();
        if (dataList.isEmpty()) {
            initializeData();
            dataList = findAll();
        }
        return dataList.get(0);

    }

    public GlobalData getData() {
        if (data == null) {
            data = importOrCreateData();
        }

        return data;
    }

    public UserModel getLoginnedUser() {
        return getData().getLoginnedUser();
    }

    public void setLoginnedUserByUsername(String username) {
        getData().setLoginnedUsername(username);
        update(getData());
        data = importOrCreateData();
    }

    public void setLogoutUser() {
        setLoginnedUserByUsername(null);
    }

    private static class SingletonHelper {
        private static final GlobalDataDao INSTANCE = new GlobalDataDao();
    }

    public static GlobalDataDao getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
