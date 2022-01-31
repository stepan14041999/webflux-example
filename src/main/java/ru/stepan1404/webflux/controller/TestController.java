package ru.stepan1404.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

    private static final String CONTEXT_KEY = "KEY";

    @GetMapping
    public Mono<String> test() {
        return some();
    }

    private Mono<String> some() {
        return Mono.deferContextual(contextView -> Mono.just(contextView.<String>get(CONTEXT_KEY)))
                .contextWrite(context -> context.put(CONTEXT_KEY, "Hello, world"));
    }
}
