package com.boursinos.cryptosse;

import com.boursinos.cryptosse.clients.CryptoClients;
import com.boursinos.cryptosse.model.Coin;
import com.boursinos.cryptosse.model.response.CoinResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.List;

@SpringBootTest
class CryptoSseApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		CryptoClients cryptoClients = new CryptoClients();
		CoinResponse coin = cryptoClients.getCurrentData("usd","bitcoin");
		System.out.println(coin);
	}

}
