package com.antecedentes.api_antecedentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AntecedenteMedicamentoDTO {
    @NotNull(message = "El ID del medicamento es obligatorio")
    private Long medicamentoId;

    private String nombreMedicamento; // Solo lectura, para mostrar en respuesta

    @NotBlank(message = "La dosis no puede estar vacía")
    @Size(max = 100, message = "La dosis no debe exceder los 100 caracteres")
    private String dosis;
}
