package com.recetas.api_recetas.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.recetas.api_recetas.dto.AlimentosDto;

@Service
public class ExternalServiceClient {

    private final WebClient webClient;

    public ExternalServiceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public AlimentosDto obtenerAlimento(Long id_alimento) {
        try{ 
            return webClient
                .get()
                .uri("http://localhost:8082/api/alimentos/" + id_alimento)
                .retrieve()
                .bodyToMono(AlimentosDto.class)
                .block();
        } catch (Exception e){
            return null;
        }
    }
}
