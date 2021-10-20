package com.aloha.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CurrencyExchange {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_exchg")
    @SequenceGenerator(name = "seq_exchg", allocationSize = 1)
    @Id
    private Long id;

    @Column(name = "from_currency")
    private String from;

    @Column(name="to_currency")
    private String to;
    private BigDecimal conversionMultiplier;

    @Transient
    private String environment;

}
