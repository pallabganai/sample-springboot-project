package org.example.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.services.config.FactConfig;
import org.example.services.entity.FactResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Slf4j
public class FactSourceImpl implements MyRestClient {
    private static Logger logger = LoggerFactory.getLogger(FactSourceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    FactConfig factConfig;

    @Override
    public FactResponse callExternal() {
        logger.info("factConfig url - " +factConfig.getUrl());

        ResponseEntity<FactResponse> factResponseResponseEntity = restTemplate.exchange("https://catfact.ninja/fact", HttpMethod.GET, HttpEntity.EMPTY, FactResponse.class);
        logger.info("Response code - " +factResponseResponseEntity.getStatusCode());

        return factResponseResponseEntity.getBody();
    }
}