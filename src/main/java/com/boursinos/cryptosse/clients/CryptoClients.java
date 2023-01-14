package com.boursinos.cryptosse.clients;

import com.boursinos.cryptosse.model.Coin;
import com.boursinos.cryptosse.model.response.CoinResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

public class CryptoClients {
    RestTemplate restTemplate = new RestTemplate();

    public CoinResponse getCurrentData(String currency, String id) throws JsonProcessingException {
        String url = "https://api.coingecko.com/api/v3/coins/markets";
        Map<String, String> uriParam = new HashMap<>();
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("vs_currency", currency)
                .queryParam("ids", id).build();

        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                String.class, uriParam);

        ObjectMapper mapper = new ObjectMapper();
        List<LinkedHashMap> coins = mapper.readValue(response.getBody(), List.class);
        Integer price = (Integer) coins.get(0).get("current_price");

        return new CoinResponse(id,price);
    }
}
