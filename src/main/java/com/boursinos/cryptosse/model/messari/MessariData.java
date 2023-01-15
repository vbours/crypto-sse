package com.boursinos.cryptosse.model.messari;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class MessariData {
    private Status status;
    private Data data;

}
