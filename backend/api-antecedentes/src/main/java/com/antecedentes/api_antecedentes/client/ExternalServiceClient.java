package com.antecedentes.api_antecedentes.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.antecedentes.api_antecedentes.dto.PacienteDTO;

@Service
public class ExternalServiceClient {

    private final WebClient webClient;

    public ExternalServiceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public PacienteDTO obtenerPaciente(Long id_paciente) {
        try {
            return webClient
                    .get()
                    .uri("http://localhost:8085/api/v1/pacientes/" + id_paciente)
                    .retrieve()
                    .bodyToMono(PacienteDTO.class)
                    .block();
        } catch (Exception e) {
            return null;
        }
    }
}
