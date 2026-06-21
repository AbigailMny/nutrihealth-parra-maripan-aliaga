package com.mediciones.api_mediciones.service;

import java.util.List;

import com.mediciones.api_mediciones.dto.MedicionRequest;
import com.mediciones.api_mediciones.dto.MedicionResponse;

public interface MedicionService {
    MedicionResponse createMedicion(MedicionRequest request);
    MedicionResponse getMedicionById(Long id);
    List<MedicionResponse> getMedicionesByPacienteId(Long pacienteId);
    List<MedicionResponse> getAllMediciones();
    MedicionResponse updateMedicion(Long id, MedicionRequest request);
    void deleteMedicion(Long id);
}
