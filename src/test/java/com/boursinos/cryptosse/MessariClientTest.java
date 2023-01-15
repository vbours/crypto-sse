package com.boursinos.cryptosse;

import com.boursinos.cryptosse.clients.CryptoClient;
import com.boursinos.cryptosse.model.response.CoinResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class MessariClientTest {
	static final Logger logger = Logger.getLogger(MessariClientTest.class);

	@Autowired
	@Qualifier("MessariClientImpl")
	private CryptoClient cryptoClient;
	@Test
	void getMessariResults() throws JsonProcessingException {
		CoinResponse coin = cryptoClient.getCurrentData("usd","bitcoin");
		logger.debug(coin);
		Assert.isTrue(coin!=null);
	}

}
