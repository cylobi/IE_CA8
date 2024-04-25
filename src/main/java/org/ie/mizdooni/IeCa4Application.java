package org.ie.mizdooni;

import org.ie.mizdooni.model.InitializerAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IeCa4Application {

    public static void main(String[] args) {

        initializeModelsFromApi();
        SpringApplication.run(IeCa4Application.class, args);


    }
    protected static void initializeModelsFromApi(){
        var initApi = new InitializerAPI();
        initApi.initializeModels();
    }

}
