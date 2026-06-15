package com.antecedentes.api_antecedentes.service.impl;

import com.antecedentes.api_antecedentes.dto.MedicamentoDTO;
import com.antecedentes.api_antecedentes.exception.ResourceNotFoundException;
import com.antecedentes.api_antecedentes.model.Medicamento;
import com.antecedentes.api_antecedentes.repository.MedicamentoRepository;
import com.antecedentes.api_antecedentes.service.MedicamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    private MedicamentoDTO mapToDTO(Medicamento medicamento) {
        return MedicamentoDTO.builder()
                .id(medicamento.getId())
                .nombre(medicamento.getNombre())
                .descripcion(medicamento.getDescripcion())
                .build();
    }

    private Medicamento mapToEntity(MedicamentoDTO dto) {
        return Medicamento.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }

    @Override
    public List<MedicamentoDTO> getAllMedicamentos() {
        return medicamentoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentoDTO getMedicamentoById(Long id) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento", "id", id));
        return mapToDTO(medicamento);
    }

    @Override
    public MedicamentoDTO createMedicamento(MedicamentoDTO medicamentoDTO) {
        Medicamento medicamento = mapToEntity(medicamentoDTO);
        Medicamento savedMedicamento = medicamentoRepository.save(medicamento);
        return mapToDTO(savedMedicamento);
    }

    @Override
    public MedicamentoDTO updateMedicamento(Long id, MedicamentoDTO medicamentoDTO) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento", "id", id));

        medicamento.setNombre(medicamentoDTO.getNombre());
        medicamento.setDescripcion(medicamentoDTO.getDescripcion());

        Medicamento updatedMedicamento = medicamentoRepository.save(medicamento);
        return mapToDTO(updatedMedicamento);
    }

    @Override
    public void deleteMedicamento(Long id) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento", "id", id));
        medicamentoRepository.delete(medicamento);
    }
}
