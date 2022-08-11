package com.mycompany.app.configuration;

import com.mycompany.app.dataprovider.entity.MyAppUserEntity;
import com.mycompany.app.dataprovider.repository.MyAppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = {"com.mycompany.app"})
public class MyAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }

    /**
     * Dummy data set using JPA
     * @param repo
     * @return
     */
    @Bean
    public CommandLineRunner demoData(MyAppUserRepository repo) {
        Optional<MyAppUserEntity> myAppUser = repo.findByUserName("myappuser");

        if(!myAppUser.isPresent()) {
            MyAppUserEntity myAppUserNew = new MyAppUserEntity();
            myAppUserNew.setUserName("myappuser");
            myAppUserNew.setPassword("myapppass");
            myAppUserNew.setActive(true);
            myAppUserNew.setRoles("ROLE_USER,ROLE_ADMIN");

            return args -> {
                repo.save(myAppUserNew);
            };
        } else {
            return args -> {
                myAppUser.get();
            };
        }
    }
}
