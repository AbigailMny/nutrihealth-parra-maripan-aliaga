package com.servicio.paciente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

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

    private Long tipoDietaId;
    private TipoDietaDTO tipoDieta;

    private LocalDate fechaRegistro;
}
