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
        String QueryString = "from GlobalData";
        var allData = session.createQuery(QueryString, GlobalData.class).getResultList();
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

    public void updateRecord(GlobalData record) {
        configuration.addAnnotatedClass(GlobalData.class);
        startSession();
        begin();
        session.merge(record);
        commit();
        endSession();
    }
    public void deleteRecord(GlobalData record) {
        configuration.addAnnotatedClass(GlobalData.class);
        startSession();
        begin();
        session.remove(record);
        commit();
        endSession();
    }

    public GlobalData getOne(Long id) {
        configuration.addAnnotatedClass(GlobalData.class);
        startSession();
        begin();
        GlobalData globalData = session.get(GlobalData.class, id);
        commit();
        endSession();
        return globalData;
    }
}
