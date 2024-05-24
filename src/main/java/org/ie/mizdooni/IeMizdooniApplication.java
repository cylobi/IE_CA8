package org.ie.mizdooni;

import org.hibernate.cfg.Configuration;
import org.ie.mizdooni.auth.AuthenticationService;
import org.ie.mizdooni.auth.RegisterRequest;
import org.ie.mizdooni.dao.GlobalDataDao;
import org.ie.mizdooni.model.GlobalData;
import org.ie.mizdooni.model.InitializerAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.ie.mizdooni.user.Role.ADMIN;
import static org.ie.mizdooni.user.Role.MANAGER;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
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

    @Bean
    public CommandLineRunner commandLineRunner(AuthenticationService service) {
        return args -> {
            var admin = RegisterRequest.builder().firstname("Admin").lastname("Admin").email("admin@mail.com")
                    .password("password").role(ADMIN).build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());

            var manager = RegisterRequest.builder().firstname("Admin").lastname("Admin").email("manager@mail.com")
                    .password("password").role(MANAGER).build();
            System.out.println("Manager token: " + service.register(manager).getAccessToken());

        };
    }

}
