package org.ie.mizdooni.dao;

import java.util.List;

import org.ie.mizdooni.model.GlobalData;

public class GlobalDataDao extends BaseDao {
    public void begin() {
        session.getTransaction().begin();
    }

    public void commit() {
        session.getTransaction().commit();
    }

    public List<GlobalData> getAll() {
        configuration.addAnnotatedClass(GlobalData.class);
        startSession();
        begin();
        var allData = session.createQuery("from GlobalData").list();
        commit();
        endSession();
        return allData;
    }

    public void saveRecord(GlobalData record) {
        configuration.addAnnotatedClass(GlobalData.class);
        startSession();
        begin();
        session.persist(record);
        commit();
        endSession();
    }
}
