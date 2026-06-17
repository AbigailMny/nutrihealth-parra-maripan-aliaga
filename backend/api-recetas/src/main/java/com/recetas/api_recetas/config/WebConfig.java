package com.recetas.api_recetas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

    @Value("${security.internal.secret}")
    private String internalSecret;

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .defaultHeader("X-Internal-Request", internalSecret)
                .build();
    }
}
