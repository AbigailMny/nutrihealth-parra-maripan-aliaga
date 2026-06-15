package com.antecedentes.api_antecedentes.service;

import com.antecedentes.api_antecedentes.dto.AlergiaDTO;
import java.util.List;

public interface AlergiaService {
    List<AlergiaDTO> getAllAlergias();
    AlergiaDTO getAlergiaById(Long id);
    AlergiaDTO createAlergia(AlergiaDTO alergiaDTO);
    AlergiaDTO updateAlergia(Long id, AlergiaDTO alergiaDTO);
    void deleteAlergia(Long id);
}
