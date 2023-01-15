package com.boursinos.cryptosse.model.messari;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Data {
    private String symbol;
    private MarketData market_data;
}
