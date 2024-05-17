package org.ie.mizdooni;

import org.hibernate.cfg.Configuration;
import org.ie.mizdooni.dao.GlobalDataDao;
import org.ie.mizdooni.model.GlobalData;
import org.ie.mizdooni.model.InitializerAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import java.persistence.Persistence;

@SpringBootApplication
public class IeMizdooniApplication {

    public static void main(String[] args) {
        // importGlobalDataFromDatabase();
        initializeModelsFromApi();
        setTestUser();
        SpringApplication.run(IeMizdooniApplication.class, args);

    }

    protected static void setTestUser() {
        var dao = new GlobalDataDao();
        dao.setLoginnedUserByUsername("MohammadJavad_Afsari");
    }

    protected static void importGlobalDataFromDatabase() {
        var dao = new GlobalDataDao();
        var gdata = dao.findAll();
        GlobalData newData = new GlobalData();
        newData.setIsUserLoginned(false);
        newData.setVersion("2.00Beta");
        newData.setId(2);
        dao.deleteById(3);
    }

    protected static void initializeModelsFromApi() {
        var initApi = new InitializerAPI();
        initApi.initializeModels();
    }

}
