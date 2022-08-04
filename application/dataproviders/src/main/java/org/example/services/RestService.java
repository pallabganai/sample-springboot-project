package org.example.services;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * a generic client implementation to make call to rest services.
 */
@Slf4j
@Service
public class RestService<T> {
    @Lazy
    @Autowired
    @Setter
    private RestTemplate restTemplate;
    @Autowired
    @Setter
    private MessageSource messageSource;

    @Autowired
    @Setter
    HttpServletRequest httpRequest;

    public ResponseEntity<T> exchange(String serviceEndPoint, HttpMethod httpMethod, HttpEntity request,
                                        Class<T> clazz) {
        try {
            return restTemplate.exchange(serviceEndPoint, httpMethod, request, clazz);
        } catch(HttpServerErrorException.InternalServerError se) {
            log.error("Internal server error occurs:{}", se.getMessage());
            throw se;
        } catch (RestClientException re) {
            log.error("Error occurs in Rest Endpoint invocation: {}", re);
            throw re;
        }
    }
    /**
     * Create instance as RestTemplate.
     *
     * @return RestTemplate instance
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}