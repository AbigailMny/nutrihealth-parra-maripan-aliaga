package com.rutinas.api_rutinas.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.rutinas.api_rutinas.dto.PacienteDto;

@Service
public class ExternalServiceClient {

    private final WebClient webClient;

    public ExternalServiceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Consulta el microservicio de pacientes para verificar si un paciente existe.
     *
     * @param pacienteId ID del paciente a buscar
     * @return PacienteDto con los datos del paciente, o null si no existe o si el servicio no está disponible
     */
    public PacienteDto obtenerPaciente(Long pacienteId) {
        try {
            return webClient
                    .get()
                    .uri("http://localhost:8085/api/v1/pacientes/" + pacienteId)
                    .retrieve()
                    .bodyToMono(PacienteDto.class)
                    .block();
        } catch (Exception e) {
            return null;
        }
    }
}
