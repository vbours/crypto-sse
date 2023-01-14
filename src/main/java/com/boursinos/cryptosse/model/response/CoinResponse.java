package com.boursinos.cryptosse.model.response;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CoinResponse {
    private String name;
    private int currentPrice;
}
