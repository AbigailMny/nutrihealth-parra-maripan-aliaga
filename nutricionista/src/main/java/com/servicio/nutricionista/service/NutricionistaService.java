package com.servicio.nutricionista.service;

import com.servicio.nutricionista.dto.NutricionistaDTO;
import java.util.List;

public interface NutricionistaService {
    List<NutricionistaDTO> findAll();
    NutricionistaDTO findById(Long id);
    NutricionistaDTO save(NutricionistaDTO nutricionistaDTO);
    NutricionistaDTO update(Long id, NutricionistaDTO nutricionistaDTO);
    void delete(Long id);
}
