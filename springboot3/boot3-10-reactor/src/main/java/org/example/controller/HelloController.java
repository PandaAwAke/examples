package org.example.controller;


import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(
            @RequestParam(value = "key", required = false, defaultValue = "default") String key
    ) {
        return "Hello, world! key = " + key;
    }

    @GetMapping("/webflux/hello")
    public Mono<String> webfluxHello(
            @RequestParam(value = "key", required = false, defaultValue = "default") String key
    ) {
        return Mono.just("Hello, world! key = " + key);
    }

    @GetMapping("/webflux/multi")
    public Flux<String> webfluxMulti() {
        return Flux.just("v1", "v2", "v3", "v4");
    }

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> sse() {
        return Flux.range(1, 11)
                .map(i -> "message " + i)
                .delayElements(Duration.ofMillis(300));
    }

    @GetMapping(value = "/sse2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> sse2() {
        return Flux.range(1, 11)
                .map(i -> ServerSentEvent.builder("message " + i)
                        .id("id" + i)
                        .comment("这是备注")
                        .event("这是事件")
                        .build())
                .delayElements(Duration.ofMillis(300));
    }

}
