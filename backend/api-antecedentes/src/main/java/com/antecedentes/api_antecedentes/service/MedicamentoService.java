package com.antecedentes.api_antecedentes.service;

import com.antecedentes.api_antecedentes.dto.MedicamentoDTO;
import java.util.List;

public interface MedicamentoService {
    List<MedicamentoDTO> getAllMedicamentos();
    MedicamentoDTO getMedicamentoById(Long id);
    MedicamentoDTO createMedicamento(MedicamentoDTO medicamentoDTO);
    MedicamentoDTO updateMedicamento(Long id, MedicamentoDTO medicamentoDTO);
    void deleteMedicamento(Long id);
}
