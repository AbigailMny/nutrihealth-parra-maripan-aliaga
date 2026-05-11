package com.servicio.paciente.service;

import com.servicio.paciente.dto.PacienteDTO;
import java.util.List;

public interface PacienteService {
    List<PacienteDTO> findAll();
    PacienteDTO findById(Long id);
    PacienteDTO save(PacienteDTO pacienteDTO);
    PacienteDTO update(Long id, PacienteDTO pacienteDTO);
    void delete(Long id);
}
