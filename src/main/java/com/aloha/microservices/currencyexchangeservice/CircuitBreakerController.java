package com.aloha.microservices.currencyexchangeservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CircuitBreakerController {

    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    @GetMapping("/sample-api")
    public String sampleApi() {
        log.info("sample api call received");
        ResponseEntity<String> resp = new RestTemplate().getForEntity("http://localhost:8080/dummy-api", String.class);
        return resp.getBody();
    }

    public String hardcodedResponse(Throwable exception) {
        log.info("Return fallback response");
        return "fallback response";
    }

}
