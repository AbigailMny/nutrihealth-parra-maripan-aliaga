package com.servicios.cita.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.servicios.cita.dto.NutricionistaDto;
import com.servicios.cita.dto.PacienteDto;

@Service
public class ExternalServiceClient {

    private final WebClient webClient;

    public ExternalServiceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public PacienteDto obtenerPaciente(Long id_paciente) {
        try {
            return webClient
                    .get()
                    .uri("http://localhost:8085/api/v1/pacientes/" + id_paciente)
                    .retrieve()
                    .bodyToMono(PacienteDto.class)
                    .block();
        } catch (Exception e) {
            return null;
        }
    }

    public NutricionistaDto obtenerNutricionista(Long id_nutricionista) {
        try {
            return webClient
                    .get()
                    .uri("http://localhost:8081/api/v1/nutricionistas/" + id_nutricionista)
                    .retrieve()
                    .bodyToMono(NutricionistaDto.class)
                    .block();
        } catch (Exception e) {
            return null;
        }
    }

}
