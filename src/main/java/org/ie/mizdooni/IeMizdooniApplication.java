package org.ie.mizdooni;

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GlobalDataManager");
        EntityManager em = emf.createEntityManager();
        var newData = new GlobalData();
        em.getTransaction().begin();
        newData.setIsUserLoginned(false);
        newData.setVersion("1.00Alpha");
        em.persist(newData);
        em.getTransaction().commit();
        emf.close();
        em.close();
    }

    protected static void initializeModelsFromApi() {
        var initApi = new InitializerAPI();
        initApi.initializeModels();
    }

}
