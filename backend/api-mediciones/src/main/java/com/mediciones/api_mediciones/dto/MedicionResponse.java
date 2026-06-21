package com.mediciones.api_mediciones.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicionResponse {

    private Long id;
    private Long pacienteId;
    private Date fechaMedicion;
    private BigDecimal pesoKg;
    private BigDecimal tallaCm;
    private BigDecimal grasaPorcentaje;
    private BigDecimal musculoPorcentaje;
}
