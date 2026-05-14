package com.servicio.paciente.service.impl;

import com.servicio.paciente.dto.TipoDietaDTO;
import com.servicio.paciente.model.TipoDieta;
import com.servicio.paciente.repository.TipoDietaRepository;
import com.servicio.paciente.service.TipoDietaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoDietaServiceImpl implements TipoDietaService {

    @Autowired
    private TipoDietaRepository tipoDietaRepository;

    @Override
    public List<TipoDietaDTO> findAll() {
        return tipoDietaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TipoDietaDTO findById(Long id) {
        TipoDieta tipoDieta = tipoDietaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de dieta no encontrado con id: " + id));
        return mapToDTO(tipoDieta);
    }

    @Override
    public TipoDietaDTO save(TipoDietaDTO tipoDietaDTO) {
        TipoDieta tipoDieta = mapToEntity(tipoDietaDTO);
        TipoDieta saved = tipoDietaRepository.save(tipoDieta);
        return mapToDTO(saved);
    }

    @Override
    public TipoDietaDTO update(Long id, TipoDietaDTO tipoDietaDTO) {
        TipoDieta tipoDieta = tipoDietaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de dieta no encontrado con id: " + id));
        
        tipoDieta.setNombre(tipoDietaDTO.getNombre());
        tipoDieta.setDescripcion(tipoDietaDTO.getDescripcion());
        
        TipoDieta updated = tipoDietaRepository.save(tipoDieta);
        return mapToDTO(updated);
    }

    @Override
    public void delete(Long id) {
        TipoDieta tipoDieta = tipoDietaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de dieta no encontrado con id: " + id));
        tipoDietaRepository.delete(tipoDieta);
    }

    private TipoDietaDTO mapToDTO(TipoDieta tipoDieta) {
        return TipoDietaDTO.builder()
                .id(tipoDieta.getId())
                .nombre(tipoDieta.getNombre())
                .descripcion(tipoDieta.getDescripcion())
                .build();
    }

    private TipoDieta mapToEntity(TipoDietaDTO dto) {
        return TipoDieta.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }
}
