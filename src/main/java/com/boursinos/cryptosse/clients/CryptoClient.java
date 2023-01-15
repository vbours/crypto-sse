package com.boursinos.cryptosse.clients;

import com.boursinos.cryptosse.model.response.CoinResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CryptoClient {

    CoinResponse getCurrentData(String currency, String id) throws JsonProcessingException;

}
