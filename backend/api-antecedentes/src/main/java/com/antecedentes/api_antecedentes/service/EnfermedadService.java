package com.antecedentes.api_antecedentes.service;

import com.antecedentes.api_antecedentes.dto.EnfermedadDTO;
import java.util.List;

public interface EnfermedadService {
    List<EnfermedadDTO> getAllEnfermedades();
    EnfermedadDTO getEnfermedadById(Long id);
    EnfermedadDTO createEnfermedad(EnfermedadDTO enfermedadDTO);
    EnfermedadDTO updateEnfermedad(Long id, EnfermedadDTO enfermedadDTO);
    void deleteEnfermedad(Long id);
}
