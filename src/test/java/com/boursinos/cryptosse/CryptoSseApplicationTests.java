package com.boursinos.cryptosse;

import com.boursinos.cryptosse.clients.MessariClientImpl;
import com.boursinos.cryptosse.clients.CoinGeckoClientImpl;
import com.boursinos.cryptosse.model.response.CoinResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CryptoSseApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		CoinGeckoClientImpl cryptoClient = new CoinGeckoClientImpl();
		CoinResponse coin = cryptoClient.getCurrentData("usd","bitcoin");
		System.out.println(coin);
	}
	@Test
	void contextLoads2(){
		MessariClientImpl cryptoClient = new MessariClientImpl();
		String coin = String.valueOf(cryptoClient.getCurrentData("","usd"));
		System.out.println(coin);
	}

}
