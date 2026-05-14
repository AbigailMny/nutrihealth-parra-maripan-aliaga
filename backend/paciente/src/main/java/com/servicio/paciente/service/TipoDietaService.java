package com.servicio.paciente.service;

import com.servicio.paciente.dto.TipoDietaDTO;
import java.util.List;

public interface TipoDietaService {
    List<TipoDietaDTO> findAll();
    TipoDietaDTO findById(Long id);
    TipoDietaDTO save(TipoDietaDTO tipoDietaDTO);
    TipoDietaDTO update(Long id, TipoDietaDTO tipoDietaDTO);
    void delete(Long id);
}
