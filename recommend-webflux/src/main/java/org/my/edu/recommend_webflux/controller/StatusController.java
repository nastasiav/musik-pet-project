package org.my.edu.recommend_webflux.controller;

import lombok.RequiredArgsConstructor;
import org.my.edu.recommend_webflux.model.status.StatusApp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class StatusController {

    @GetMapping("/status")
    public Mono<StatusApp> getStatus() {
        return Mono.just(new StatusApp(true));
    }
}
