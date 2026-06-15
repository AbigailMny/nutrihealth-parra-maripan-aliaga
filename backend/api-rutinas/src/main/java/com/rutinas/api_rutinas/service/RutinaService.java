package com.rutinas.api_rutinas.service;

import java.util.List;

import com.rutinas.api_rutinas.dto.RutinaDTO;

public interface RutinaService {
    RutinaDTO createRutina(RutinaDTO rutinaDTO);
    RutinaDTO getRutinaById(Long id);
    List<RutinaDTO> getAllRutinas();
    List<RutinaDTO> getRutinasByPacienteId(Long pacienteId);
    RutinaDTO updateRutina(Long id, RutinaDTO rutinaDTO);
    void deleteRutina(Long id);
}
