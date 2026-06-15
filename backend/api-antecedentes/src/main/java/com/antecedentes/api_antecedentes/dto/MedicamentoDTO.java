package com.antecedentes.api_antecedentes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicamentoDTO {
    private Long id;
    @NotBlank(message = "El nombre del medicamento no puede estar vacío")
    private String nombre;
    private String descripcion;
}
