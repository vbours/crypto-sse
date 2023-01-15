package com.boursinos.cryptosse.clients;

import com.boursinos.cryptosse.model.response.CoinResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Component("CoinGeckoClientImpl")
public class CoinGeckoClientImpl implements CryptoClient {
    RestTemplate restTemplate = new RestTemplate();

    static final Logger logger = Logger.getLogger(CoinGeckoClientImpl.class);

    @Value("${coingecko.url}")
    private String url;

    public CoinResponse getCurrentData(String currency, String id) throws JsonProcessingException {
        logger.debug("API CoinGecko");
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
