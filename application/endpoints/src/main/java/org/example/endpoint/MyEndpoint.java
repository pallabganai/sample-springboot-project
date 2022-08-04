package org.example.endpoint;

import org.example.constants.Constants;
import org.example.services.MyRestClient;
import org.example.services.entity.FactResponse;
import org.example.dto.GreetingsRequest;
import org.example.dto.GreetingsResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(Constants.MYAPP_ENDPOINT)
public class MyEndpoint {
    @Autowired
    private MyRestClient myRestClient;

    @Value("${fact.url}")
    private String factUrl;

    private static final Logger logger = LoggerFactory.getLogger(MyEndpoint.class);
    private static int counter = 0;

    @GetMapping(value = Constants.GREETINGS_ENDPOINT)
    public void greetings(@RequestParam(name = "yourname") String name) {
        logger.info("greetings get...." + name);
    }

    @PostMapping(value = Constants.GREETINGS_ENDPOINT)
    public GreetingsResponse greetings(@RequestBody GreetingsRequest greetingsRequest) {
        logger.info("greetings post...." + greetingsRequest.getName());

        counter++;

        return new GreetingsResponse("Ganai " +counter);
    }

    @PostMapping(value = Constants.GREETINGS_ENDPOINT2)
    public ResponseEntity<GreetingsResponse> greetings2(@RequestBody GreetingsRequest greetingsRequest) {
        logger.info("greetings post...." + greetingsRequest.getName());

        counter++;

        logger.info("factUrl-" +factUrl);

        FactResponse factResponse = myRestClient.callExternal();

        return new ResponseEntity<>(new GreetingsResponse("Ganai, " +counter +" " +factResponse.getFact()), HttpStatus.OK);
    }
}