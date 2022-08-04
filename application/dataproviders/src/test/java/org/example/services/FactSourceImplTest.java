package org.example.services;

import org.example.services.config.FactConfig;
import org.example.services.entity.FactResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class FactSourceImplTest {
    @Mock
    RestTemplate restTemplate;

    private AutoCloseable closeable;

    @Mock
    FactConfig factConfig;

    @InjectMocks
    private MyRestClient myRestClient = new FactSourceImpl(restTemplate, factConfig);

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        //myRestClient = new FactSourceImpl(restTemplate, factConfig);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @org.junit.jupiter.api.Test
    void callExternalTest() {
        FactResponse factResponseExpected = new FactResponse();
        factResponseExpected.setFact("His is a dummy message");
        factResponseExpected.setLength(10);

        Mockito.when(factConfig.getUrl()).thenReturn("http://this.is.a.dummy.url");
        Mockito.when(restTemplate
                        .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), Mockito.<Class<FactResponse>>any()))
                .thenReturn(new ResponseEntity<FactResponse>(factResponseExpected, HttpStatus.OK));
        FactResponse factResponse = this.myRestClient.callExternal();
        assertNotNull(factResponse);
        assertNotNull(factResponse.getFact());
        assertSame(factResponseExpected.getFact(), factResponse.getFact());
        assertSame(factResponseExpected.getLength(), factResponse.getLength());
    }
}