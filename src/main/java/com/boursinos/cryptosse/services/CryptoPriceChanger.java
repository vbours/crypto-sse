package com.boursinos.cryptosse.services;

import com.boursinos.cryptosse.clients.CryptoClients;
import com.boursinos.cryptosse.model.response.CoinResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class CryptoPriceChanger {
    private final ApplicationEventPublisher publisher;

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public CryptoPriceChanger(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
        this.executor.schedule(this::changePrice, 1, SECONDS);
    }

    private void changePrice() {
        CryptoClients cryptoClients = new CryptoClients();
        CoinResponse coinResponse = null;
        try {
            coinResponse = cryptoClients.getCurrentData("usd","bitcoin");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        publisher.publishEvent(coinResponse); //6
        executor.schedule(this::changePrice, 10000, MILLISECONDS); //7
    }

}
