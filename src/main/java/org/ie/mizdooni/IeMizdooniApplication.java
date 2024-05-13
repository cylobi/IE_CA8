package org.ie.mizdooni;

import org.hibernate.cfg.Configuration;

import org.ie.mizdooni.model.GlobalData;
import org.ie.mizdooni.model.InitializerAPI;
import org.ie.mizdooni.model.repository.GlobalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// import java.persistence.Persistence;

@SpringBootApplication
public class IeMizdooniApplication {

    public static void main(String[] args) {
        importGlobalDataFromDatabase();
        initializeModelsFromApi();
        SpringApplication.run(IeMizdooniApplication.class, args);

    }

    protected static void importGlobalDataFromDatabase() {

        // GlobalDataRepository globalData;
        var configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(GlobalData.class);
        try (var sesssionFactory = configuration.buildSessionFactory()) {
            var session = sesssionFactory.openSession();

            var newData = new GlobalData();
            newData.setIsUserLoginned(false);
            newData.setVersion("1.00Alpha");
            session.getTransaction().begin();
            session.persist(newData);
            session.getTransaction().commit();
            session.close();
        }
    }

    protected static void initializeModelsFromApi() {
        var initApi = new InitializerAPI();
        initApi.initializeModels();
    }

}
