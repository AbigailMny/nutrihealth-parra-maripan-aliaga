package com.antecedentes.api_antecedentes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteDTO {
    private Long id;
    private String run;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
}
