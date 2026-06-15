package com.minutas.api_minutas.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MinutaDto {

    private Long id;
    private Long paciente_id;
    private Long nutricionista_id;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private String url_archivo;
    private String estado;

    private PacienteDto paciente;
    private NutricionistaDto nutricionista;
}
