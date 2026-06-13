package com.servicio.paciente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Modelo DTO para la definición de los tipos de dieta en el catálogo")
public class TipoDietaDTO {

    @Schema(description = "Identificador único de la dieta", example = "1")
    private Long id;

    @NotBlank(message = "El nombre de la dieta es obligatorio")
    @Schema(description = "Nombre descriptivo del tipo de dieta", example = "Keto / Cetogénica", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Schema(description = "Detalles específicos, restricciones o pautas de la dieta", example = "Dieta alta en grasas saludables, moderada en proteínas y muy baja en carbohidratos.")
    private String descripcion;
}