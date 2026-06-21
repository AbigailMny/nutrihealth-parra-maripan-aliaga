package com.mediciones.api_mediciones.service.impl;

import com.mediciones.api_mediciones.dto.MedicionRequest;
import com.mediciones.api_mediciones.dto.MedicionResponse;
import com.mediciones.api_mediciones.exception.ResourceNotFoundException;
import com.mediciones.api_mediciones.service.MedicionService;
import com.mediciones.api_mediciones.repository.MedicionRepository;
import com.mediciones.api_mediciones.model.Mediciones;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicionServiceImpl implements MedicionService {

    private final MedicionRepository medicionRepository;

    @Override
    @Transactional
    public MedicionResponse createMedicion(MedicionRequest request) {

        Mediciones medicion = Mediciones.builder()
                .pacienteId(request.getPacienteId())
                .fechaMedicion(request.getFechaMedicion())
                .pesoKg(request.getPesoKg())
                .tallaCm(request.getTallaCm())
                .grasaPorcentaje(request.getGrasaPorcentaje())
                .musculoPorcentaje(request.getMusculoPorcentaje())
                .build();

        Mediciones savedMedicion = medicionRepository.save(medicion);

        return mapToResponse(savedMedicion);
    }

    @Override
    @Transactional(readOnly = true)
    public MedicionResponse getMedicionById(Long id) {

        Mediciones medicion = medicionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicion", "id", id));

        return mapToResponse(medicion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicionResponse> getMedicionesByPacienteId(Long pacienteId) {

        return medicionRepository.findByPacienteId(pacienteId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicionResponse> getAllMediciones() {
        return medicionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MedicionResponse updateMedicion(Long id, MedicionRequest request) {

        Mediciones medicion = medicionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicion", "id", id));

        medicion.setPacienteId(request.getPacienteId());
        medicion.setFechaMedicion(request.getFechaMedicion());
        medicion.setPesoKg(request.getPesoKg());
        medicion.setTallaCm(request.getTallaCm());
        medicion.setGrasaPorcentaje(request.getGrasaPorcentaje());
        medicion.setMusculoPorcentaje(request.getMusculoPorcentaje());

        Mediciones updatedMedicion = medicionRepository.save(medicion);

        return mapToResponse(updatedMedicion);
    }

    @Override
    @Transactional
    public void deleteMedicion(Long id) {

        Mediciones medicion = medicionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicion", "id", id));

        medicionRepository.delete(medicion);
    }

    private MedicionResponse mapToResponse(Mediciones medicion) {

        return MedicionResponse.builder()
                .id(medicion.getId())
                .pacienteId(medicion.getPacienteId())
                .fechaMedicion(medicion.getFechaMedicion())
                .pesoKg(medicion.getPesoKg())
                .tallaCm(medicion.getTallaCm())
                .grasaPorcentaje(medicion.getGrasaPorcentaje())
                .musculoPorcentaje(medicion.getMusculoPorcentaje())
                .build();
    }
}