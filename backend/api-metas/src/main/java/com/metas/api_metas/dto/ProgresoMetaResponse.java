package com.metas.api_metas.dto;

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
public class ProgresoMetaResponse {
    private Long id;
    private Long metaId;
    private LocalDate fechaRegistro;
    private BigDecimal valorAlcanzado;
}
