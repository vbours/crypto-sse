package com.boursinos.cryptosse.services;

import com.boursinos.cryptosse.clients.CryptoClient;
import com.boursinos.cryptosse.model.response.CoinResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class CryptoPriceChanger {

    static final Logger logger = Logger.getLogger(CryptoPriceChanger.class);

    @Value("${currency}")
    private String currency;

    @Value("${crypto.name}")
    private String name;

    @Autowired
    @Qualifier("MessariClientImpl")
    private CryptoClient cryptoClient;
    private final ApplicationEventPublisher publisher;

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public CryptoPriceChanger(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
        this.executor.schedule(this::changePrice, 1, SECONDS);
    }

    private void changePrice() {
        CoinResponse coinResponse;
        try {
            coinResponse = cryptoClient.getCurrentData(currency,name);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        publisher.publishEvent(coinResponse);
        executor.schedule(this::changePrice, 1000, MILLISECONDS);
    }

}
