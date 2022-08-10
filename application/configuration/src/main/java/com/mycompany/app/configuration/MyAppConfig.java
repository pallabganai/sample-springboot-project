package com.mycompany.app.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EnableJpaRepositories({"com.mycompany.app.dataprovider"})
@EntityScan(basePackages = {"com.mycompany.app.dataprovider"})
@Configuration
public class MyAppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    };
}
