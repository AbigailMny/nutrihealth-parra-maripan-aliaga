package com.rutinas.api_rutinas.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RutinaDTO {

    private Long id;

    @NotNull(message = "El ID del paciente es obligatorio")
    private Long pacienteId;

    // Datos del paciente (enriquecidos desde api-pacientes, solo lectura)
    private PacienteDto paciente;

    @NotBlank(message = "El nombre de la rutina es obligatorio")
    private String nombre;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;

    @Valid
    private List<EjercicioRutinaDTO> ejercicios;
}
