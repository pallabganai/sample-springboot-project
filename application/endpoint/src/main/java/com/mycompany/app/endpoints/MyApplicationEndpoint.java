package com.mycompany.app.endpoints;

import com.mycompany.app.core.entity.CatFactsResponse;
import com.mycompany.app.core.services.CatFacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myapp")
public class MyApplicationEndpoint {
    @Autowired
    CatFacts catFacts;

    @GetMapping("/myfunc")
    public ResponseEntity<CatFactsResponse> myFunction() {
        System.out.println("I am inside the func");

        CatFactsResponse catFactsResponse = catFacts.getFacts();

        System.out.println("cat facts called");

        return new ResponseEntity<CatFactsResponse>(catFactsResponse, HttpStatus.OK);
    }
}
