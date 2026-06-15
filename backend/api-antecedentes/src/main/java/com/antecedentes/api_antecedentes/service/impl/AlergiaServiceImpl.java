package com.antecedentes.api_antecedentes.service.impl;

import com.antecedentes.api_antecedentes.dto.AlergiaDTO;
import com.antecedentes.api_antecedentes.exception.ResourceNotFoundException;
import com.antecedentes.api_antecedentes.model.Alergia;
import com.antecedentes.api_antecedentes.repository.AlergiaRepository;
import com.antecedentes.api_antecedentes.service.AlergiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlergiaServiceImpl implements AlergiaService {

    private final AlergiaRepository alergiaRepository;

    private AlergiaDTO mapToDTO(Alergia alergia) {
        return AlergiaDTO.builder()
                .id(alergia.getId())
                .nombre(alergia.getNombre())
                .descripcion(alergia.getDescripcion())
                .build();
    }

    private Alergia mapToEntity(AlergiaDTO dto) {
        return Alergia.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }

    @Override
    public List<AlergiaDTO> getAllAlergias() {
        return alergiaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlergiaDTO getAlergiaById(Long id) {
        Alergia alergia = alergiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alergia", "id", id));
        return mapToDTO(alergia);
    }

    @Override
    public AlergiaDTO createAlergia(AlergiaDTO alergiaDTO) {
        Alergia alergia = mapToEntity(alergiaDTO);
        Alergia savedAlergia = alergiaRepository.save(alergia);
        return mapToDTO(savedAlergia);
    }

    @Override
    public AlergiaDTO updateAlergia(Long id, AlergiaDTO alergiaDTO) {
        Alergia alergia = alergiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alergia", "id", id));

        alergia.setNombre(alergiaDTO.getNombre());
        alergia.setDescripcion(alergiaDTO.getDescripcion());

        Alergia updatedAlergia = alergiaRepository.save(alergia);
        return mapToDTO(updatedAlergia);
    }

    @Override
    public void deleteAlergia(Long id) {
        Alergia alergia = alergiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alergia", "id", id));
        alergiaRepository.delete(alergia);
    }
}
