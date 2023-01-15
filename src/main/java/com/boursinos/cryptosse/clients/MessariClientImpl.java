package com.boursinos.cryptosse.clients;

import com.boursinos.cryptosse.model.messari.MessariData;
import com.boursinos.cryptosse.model.response.CoinResponse;
import com.boursinos.cryptosse.services.CryptoPriceChanger;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component("MessariClientImpl")
public class MessariClientImpl implements CryptoClient {
    RestTemplate restTemplate = new RestTemplate();

    static final Logger logger = Logger.getLogger(MessariClientImpl.class);

    @Value("${messari.url}")
    private String url;
    public CoinResponse getCurrentData(String currency, String id){
        logger.debug("API Messari");
        Map<String, String> uriParam = new HashMap<>();
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).build();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<MessariData> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity,
                MessariData.class, uriParam);
        return new CoinResponse(response.getBody().getData().getSymbol(),response.getBody().getData().getMarket_data().getPrice_usd());
    }
}

