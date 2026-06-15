package com.antecedentes.api_antecedentes.service.impl;

import com.antecedentes.api_antecedentes.dto.EnfermedadDTO;
import com.antecedentes.api_antecedentes.exception.ResourceNotFoundException;
import com.antecedentes.api_antecedentes.model.Enfermedad;
import com.antecedentes.api_antecedentes.repository.EnfermedadRepository;
import com.antecedentes.api_antecedentes.service.EnfermedadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnfermedadServiceImpl implements EnfermedadService {

    private final EnfermedadRepository enfermedadRepository;

    private EnfermedadDTO mapToDTO(Enfermedad enfermedad) {
        return EnfermedadDTO.builder()
                .id(enfermedad.getId())
                .nombre(enfermedad.getNombre())
                .descripcion(enfermedad.getDescripcion())
                .build();
    }

    private Enfermedad mapToEntity(EnfermedadDTO dto) {
        return Enfermedad.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }

    @Override
    public List<EnfermedadDTO> getAllEnfermedades() {
        return enfermedadRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EnfermedadDTO getEnfermedadById(Long id) {
        Enfermedad enfermedad = enfermedadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enfermedad", "id", id));
        return mapToDTO(enfermedad);
    }

    @Override
    public EnfermedadDTO createEnfermedad(EnfermedadDTO enfermedadDTO) {
        Enfermedad enfermedad = mapToEntity(enfermedadDTO);
        Enfermedad savedEnfermedad = enfermedadRepository.save(enfermedad);
        return mapToDTO(savedEnfermedad);
    }

    @Override
    public EnfermedadDTO updateEnfermedad(Long id, EnfermedadDTO enfermedadDTO) {
        Enfermedad enfermedad = enfermedadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enfermedad", "id", id));

        enfermedad.setNombre(enfermedadDTO.getNombre());
        enfermedad.setDescripcion(enfermedadDTO.getDescripcion());

        Enfermedad updatedEnfermedad = enfermedadRepository.save(enfermedad);
        return mapToDTO(updatedEnfermedad);
    }

    @Override
    public void deleteEnfermedad(Long id) {
        Enfermedad enfermedad = enfermedadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enfermedad", "id", id));
        enfermedadRepository.delete(enfermedad);
    }
}
