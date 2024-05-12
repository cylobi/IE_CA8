package org.ie.mizdooni;

import org.ie.mizdooni.model.InitializerAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IeMizdooniApplication {

    public static void main(String[] args) {

        initializeModelsFromApi();
        SpringApplication.run(IeMizdooniApplication.class, args);

    }

    protected static void initializeModelsFromApi() {
        var initApi = new InitializerAPI();
        initApi.initializeModels();
    }

}
