package com.rutinas.api_rutinas.dto;

import jakarta.validation.constraints.Min;
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
public class EjercicioRutinaDTO {

    private Long id;

    @NotBlank(message = "El nombre del ejercicio es obligatorio")
    private String nombreEjercicio;

    @NotNull(message = "Las series son obligatorias")
    @Min(value = 1, message = "Debe tener al menos 1 serie")
    private Integer series;

    @NotNull(message = "Las repeticiones son obligatorias")
    @Min(value = 1, message = "Debe tener al menos 1 repetición")
    private Integer repeticiones;

    @NotNull(message = "El tiempo de descanso es obligatorio")
    @Min(value = 0, message = "El descanso no puede ser negativo")
    private Integer descansoSegundos;
}
