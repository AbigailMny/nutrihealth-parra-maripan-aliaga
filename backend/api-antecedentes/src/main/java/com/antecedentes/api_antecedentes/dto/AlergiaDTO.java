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
public class AlergiaDTO {
    private Long id;
    @NotBlank(message = "El nombre de la alergia no puede estar vacío")
    private String nombre;
    private String descripcion;
}
