package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class FluxController {

    @GetMapping("/error")
    public Flux<String> error() {
        return Flux.just(2, 3, 1, 0, 2)
                .map(i -> 10 / i)
                .map(String::valueOf);
    }

}
