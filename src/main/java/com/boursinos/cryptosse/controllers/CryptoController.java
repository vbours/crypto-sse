
package com.boursinos.cryptosse.controllers;

import com.boursinos.cryptosse.model.response.CoinResponse;
import org.apache.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
public class CryptoController {

    static final Logger logger = Logger.getLogger(CryptoController.class);

    private final Set<SseEmitter> clients = new CopyOnWriteArraySet<>();

    @GetMapping("/crypto-stream")
    public SseEmitter stocksStream() {
        SseEmitter sseEmitter = new SseEmitter();
        clients.add(sseEmitter);

        sseEmitter.onTimeout(() -> clients.remove(sseEmitter));
        sseEmitter.onError(throwable -> clients.remove(sseEmitter));
        return sseEmitter;
    }

    @Async
    @EventListener
    public void stockMessageHandler(CoinResponse coinResponse) {
        List<SseEmitter> errorEmitters = new ArrayList<>();
        logger.debug("Clients = " + clients.size());
        clients.forEach(emitter -> {
            try {
                logger.debug(coinResponse);
                emitter.send(coinResponse, MediaType.APPLICATION_JSON);
            } catch (Exception e) {
                errorEmitters.add(emitter);
            }
        });

        errorEmitters.forEach(clients::remove);
    }
}
