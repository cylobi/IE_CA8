package org.ie.mizdooni;

import org.hibernate.cfg.Configuration;
import org.ie.mizdooni.dao.GlobalDataDao;
import org.ie.mizdooni.model.GlobalData;
import org.ie.mizdooni.model.InitializerAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IeMizdooniApplication {

    public static void main(String[] args) {
        initializeModelsFromApi();
        setTestUser();
        SpringApplication.run(IeMizdooniApplication.class, args);

    }

    protected static void setTestUser() {
        var dao = new GlobalDataDao();
        dao.setLoginnedUserByUsername("MohammadJavad_Afsari");
    }

    protected static void initializeModelsFromApi() {
        var initApi = new InitializerAPI();
        initApi.initializeModels();
    }

}
