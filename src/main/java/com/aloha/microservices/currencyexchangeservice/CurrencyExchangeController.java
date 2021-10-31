package com.aloha.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepo repo;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeRate(@PathVariable("from") String from, @PathVariable("to") String to) {
        log.info("getExchangeRate called with from {} to {}", from, to);
        CurrencyExchange exchg = repo.findByFromAndTo(from, to);
        if (exchg == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot found currency exchange");
        exchg.setEnvironment(environment.getProperty("local.server.port"));
        return exchg;
    }

}
