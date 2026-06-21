package com.metas.api_metas.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgresoMetaRequest {

    @NotNull(message = "El ID de la meta es obligatorio")
    private Long metaId;

    @NotNull(message = "La fecha de registro es obligatoria")
    private LocalDate fechaRegistro;

    @NotNull(message = "El valor alcanzado es obligatorio")
    @DecimalMin(value = "0.0", message = "El valor alcanzado no puede ser negativo")
    private BigDecimal valorAlcanzado;
}
