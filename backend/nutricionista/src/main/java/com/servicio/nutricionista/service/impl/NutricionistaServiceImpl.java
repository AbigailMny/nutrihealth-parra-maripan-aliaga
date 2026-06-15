package com.servicio.nutricionista.service.impl;

import com.servicio.nutricionista.dto.NutricionistaDTO;
import com.servicio.nutricionista.exception.ResourceNotFoundException;
import com.servicio.nutricionista.model.Nutricionista;
import com.servicio.nutricionista.repository.NutricionistaRepository;
import com.servicio.nutricionista.service.NutricionistaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutricionistaServiceImpl implements NutricionistaService {

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    @Override
    public List<NutricionistaDTO> findAll() {
        return nutricionistaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NutricionistaDTO findById(Long id) {
        Nutricionista nutri = nutricionistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionista", "id", id));
        return mapToDTO(nutri);
    }

    @Override
    public NutricionistaDTO save(NutricionistaDTO nutricionistaDTO) {
        Nutricionista nutri = mapToEntity(nutricionistaDTO);
        Nutricionista saved = nutricionistaRepository.save(nutri);
        return mapToDTO(saved);
    }

    @Override
    public NutricionistaDTO update(Long id, NutricionistaDTO nutricionistaDTO) {
        Nutricionista nutri = nutricionistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionista", "id", id));
        
        nutri.setNombres(nutricionistaDTO.getNombres());
        nutri.setApellidos(nutricionistaDTO.getApellidos());
        nutri.setCorreo(nutricionistaDTO.getCorreo());
        nutri.setTelefono(nutricionistaDTO.getTelefono());
        
        Nutricionista updated = nutricionistaRepository.save(nutri);
        return mapToDTO(updated);
    }

    @Override
    public void delete(Long id) {
        Nutricionista nutri = nutricionistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionista", "id", id));
        nutricionistaRepository.delete(nutri);
    }

    private NutricionistaDTO mapToDTO(Nutricionista nutri) {
        return NutricionistaDTO.builder()
                .id(nutri.getId())
                .nombres(nutri.getNombres())
                .apellidos(nutri.getApellidos())
                .correo(nutri.getCorreo())
                .telefono(nutri.getTelefono())
                .build();
    }

    private Nutricionista mapToEntity(NutricionistaDTO dto) {
        return Nutricionista.builder()
                .nombres(dto.getNombres())
                .apellidos(dto.getApellidos())
                .correo(dto.getCorreo())
                .telefono(dto.getTelefono())
                .build();
    }
}
