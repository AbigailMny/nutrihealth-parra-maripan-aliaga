package com.antecedentes.api_antecedentes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
import com.antecedentes.api_antecedentes.dto.PacienteDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AntecedentesClinicosDTO {
    private Long id;
    private Long pacienteId;
    private String tipoSangre;
    private String observacionesGenerales;
    private Set<EnfermedadDTO> enfermedades;
    private Set<AlergiaDTO> alergias;
    private Set<AntecedenteMedicamentoDTO> medicamentos;
    private PacienteDTO paciente;
}
