package com.antecedentes.api_antecedentes.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Set;

@Data
public class AntecedentesClinicosRequest {
    @NotNull(message = "El ID del paciente es obligatorio")
    private Long pacienteId;

    @Size(max = 5, message = "El tipo de sangre no debe exceder los 5 caracteres")
    private String tipoSangre;

    private String observacionesGenerales;
    private Set<Long> enfermedadIds;
    private Set<Long> alergiaIds;

    @Valid
    private Set<AntecedenteMedicamentoDTO> medicamentos; // medicamentoId y dosis
}
