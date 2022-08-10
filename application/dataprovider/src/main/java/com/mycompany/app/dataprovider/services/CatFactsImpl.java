package com.mycompany.app.dataprovider.services;

import com.mycompany.app.core.entity.CatFactsResponse;
import com.mycompany.app.core.services.CatFacts;
import com.mycompany.app.dataprovider.entity.CatFactsEntity;
import com.mycompany.app.dataprovider.repository.CatFactsRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@Setter
public class CatFactsImpl implements CatFacts {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    @Setter
    CatFactsRepository catFactsRepository;

    @Override
    public CatFactsResponse getFacts() {
        ResponseEntity<CatFactsResponse> responseEntity = restTemplate.exchange(
                "https://catfact.ninja/fact",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                CatFactsResponse.class);

        log.info("Status code is - {}", responseEntity.getStatusCode());
        log.info("Status body is - {}", responseEntity.getBody());

        catFactsRepository.save(CatFactsEntity.from(responseEntity.getBody()));

        log.info("Saved");

        return responseEntity.getBody();
    }
}
