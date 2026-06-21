package com.mediciones.api_mediciones.dto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicionRequest {

    @NotNull(message = "El ID del paciente es obligatorio")
    private Long pacienteId;

    @NotNull(message = "La fecha de medición es obligatoria")
    private Date fechaMedicion;

    @NotNull(message = "El peso es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false,
        message = "El peso debe ser mayor que cero")
    private BigDecimal pesoKg;

    @NotNull(message = "La talla es obligatoria")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "La talla debe ser mayor que cero")
    private BigDecimal tallaCm;

    @NotNull(message = "El porcentaje de grasa es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "El porcentaje de grasa no puede ser negativo")
    private BigDecimal grasaPorcentaje;

    @NotNull(message = "El porcentaje de músculo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "El porcentaje de músculo no puede ser negativo")
    private BigDecimal musculoPorcentaje;
}
