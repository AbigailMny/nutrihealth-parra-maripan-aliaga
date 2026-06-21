package com.metas.api_metas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetaResponse {
    private Long id;
    private Long pacienteId;
    private String nombreMeta;
    private BigDecimal valorObjetivo;
    private String unidadMedida;
    private List<ProgresoMetaResponse> progresos;
}
