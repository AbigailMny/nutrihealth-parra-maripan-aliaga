package com.antecedentes.api_antecedentes.service;

import com.antecedentes.api_antecedentes.dto.AntecedentesClinicosDTO;
import com.antecedentes.api_antecedentes.dto.AntecedentesClinicosRequest;
import java.util.List;

public interface AntecedentesClinicosService {
    List<AntecedentesClinicosDTO> getAllAntecedentes();
    AntecedentesClinicosDTO getAntecedentesById(Long id);
    AntecedentesClinicosDTO getAntecedentesByPacienteId(Long pacienteId);
    AntecedentesClinicosDTO saveAntecedentes(AntecedentesClinicosRequest request);
    void deleteAntecedentes(Long id);
}
