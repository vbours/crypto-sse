package com.boursinos.cryptosse;

import com.boursinos.cryptosse.clients.CryptoClient;
import com.boursinos.cryptosse.model.response.CoinResponse;
import com.boursinos.cryptosse.services.CryptoPriceChanger;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CoinGeckoClientTest {
	static final Logger logger = Logger.getLogger(CoinGeckoClientTest.class);
	@Autowired
	@Qualifier("CoinGeckoClientImpl")
	private CryptoClient cryptoClient;
	@Test
	void getCoinGeckoResults() throws JsonProcessingException {
		CoinResponse coin = cryptoClient.getCurrentData("usd","bitcoin");
		logger.debug(coin);
		Assert.isTrue(coin!=null);
	}

}
