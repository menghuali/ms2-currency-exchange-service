package com.aloha.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeRate(@PathVariable("from") String from, @PathVariable("to") String to) {
        return CurrencyExchange.builder().id(1001l).from(from).to(to).conversionMultiplier(BigDecimal.valueOf(50))
                .environment(environment.getProperty("local.server.port")).build();
    }

}
