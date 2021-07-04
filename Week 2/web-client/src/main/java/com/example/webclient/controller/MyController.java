package com.example.webclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class MyController {

    WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    @GetMapping("/getuser")
    public Mono<User> getUser() {

        System.out.println("GETTING");
        Mono<User> user = webClient.get()
                .uri("/bye")
                .header("CLIENTHEADERKEY", "CLIENTHEADERVALUE")
                .retrieve()
                .bodyToMono(User.class);

        return user;
    }
}
