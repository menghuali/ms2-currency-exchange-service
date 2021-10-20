package com.aloha.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long> {

    // @Query("SELECT e FROM CurrencyExchange e WHERE e.from = ?1 AND e.to=?2")
    CurrencyExchange findByFromAndTo(String from, String to);

}
