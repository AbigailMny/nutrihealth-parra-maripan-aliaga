package com.rutinas.api_rutinas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API NutriHealth - Servicio de Rutinas")
                        .version("1.0")
                        .description("Documentación del microservicio de rutinas y ejercicios"))
                // Esto soluciona el failed to fetch
                .servers(List.of(
                        new Server().url("http://localhost:9090").description("Servidor a través del Gateway")));
    }
}
