package com.metas.api_metas.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetaRequest {

    @NotNull(message = "El ID del paciente es obligatorio")
    private Long pacienteId;

    @NotBlank(message = "El nombre de la meta es obligatorio")
    private String nombreMeta;

    @NotNull(message = "El valor objetivo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El valor objetivo debe ser mayor a 0")
    private BigDecimal valorObjetivo;

    @NotBlank(message = "La unidad de medida es obligatoria")
    private String unidadMedida;
}
