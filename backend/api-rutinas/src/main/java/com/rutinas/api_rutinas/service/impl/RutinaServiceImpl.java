package com.rutinas.api_rutinas.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rutinas.api_rutinas.client.ExternalServiceClient;
import com.rutinas.api_rutinas.dto.EjercicioRutinaDTO;
import com.rutinas.api_rutinas.dto.PacienteDto;
import com.rutinas.api_rutinas.dto.RutinaDTO;
import com.rutinas.api_rutinas.exception.ResourceNotFoundException;
import com.rutinas.api_rutinas.model.EjercicioRutina;
import com.rutinas.api_rutinas.model.Rutina;
import com.rutinas.api_rutinas.repository.RutinaRepository;
import com.rutinas.api_rutinas.service.RutinaService;

@Service
public class RutinaServiceImpl implements RutinaService {

    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired
    private ExternalServiceClient externalServiceClient;

    // =========================================================
    // CONVERSIONES (Entidad <-> DTO)
    // =========================================================

    private EjercicioRutinaDTO toEjercicioDTO(EjercicioRutina ejercicio) {
        return EjercicioRutinaDTO.builder()
                .id(ejercicio.getId())
                .nombreEjercicio(ejercicio.getNombreEjercicio())
                .series(ejercicio.getSeries())
                .repeticiones(ejercicio.getRepeticiones())
                .descansoSegundos(ejercicio.getDescansoSegundos())
                .build();
    }

    private EjercicioRutina toEjercicioEntity(EjercicioRutinaDTO dto, Rutina rutina) {
        EjercicioRutina ejercicio = new EjercicioRutina();
        ejercicio.setRutina(rutina);
        ejercicio.setNombreEjercicio(dto.getNombreEjercicio());
        ejercicio.setSeries(dto.getSeries());
        ejercicio.setRepeticiones(dto.getRepeticiones());
        ejercicio.setDescansoSegundos(dto.getDescansoSegundos());
        return ejercicio;
    }

    private RutinaDTO toRutinaDTO(Rutina rutina) {
        // Enriquecer con datos del paciente desde api-pacientes
        PacienteDto paciente = externalServiceClient.obtenerPaciente(rutina.getPacienteId());

        List<EjercicioRutinaDTO> ejerciciosDTO = rutina.getEjercicios().stream()
                .map(this::toEjercicioDTO)
                .collect(Collectors.toList());

        return RutinaDTO.builder()
                .id(rutina.getId())
                .pacienteId(rutina.getPacienteId())
                .paciente(paciente)
                .nombre(rutina.getNombre())
                .fechaInicio(rutina.getFechaInicio())
                .fechaFin(rutina.getFechaFin())
                .ejercicios(ejerciciosDTO)
                .build();
    }

    // =========================================================
    // OPERACIONES CRUD
    // =========================================================

    @Override
    @Transactional
    public RutinaDTO createRutina(RutinaDTO rutinaDTO) {
        // Validar que el paciente exista en api-pacientes
        PacienteDto paciente = externalServiceClient.obtenerPaciente(rutinaDTO.getPacienteId());
        if (paciente == null) {
            throw new ResourceNotFoundException("Paciente", "id", rutinaDTO.getPacienteId());
        }

        Rutina rutina = new Rutina();
        rutina.setPacienteId(rutinaDTO.getPacienteId());
        rutina.setNombre(rutinaDTO.getNombre());
        rutina.setFechaInicio(rutinaDTO.getFechaInicio());
        rutina.setFechaFin(rutinaDTO.getFechaFin());

        // Mapear ejercicios si existen
        if (rutinaDTO.getEjercicios() != null && !rutinaDTO.getEjercicios().isEmpty()) {
            List<EjercicioRutina> ejercicios = rutinaDTO.getEjercicios().stream()
                    .map(dto -> toEjercicioEntity(dto, rutina))
                    .collect(Collectors.toList());
            rutina.setEjercicios(ejercicios);
        }

        Rutina savedRutina = rutinaRepository.save(rutina);
        return toRutinaDTO(savedRutina);
    }

    @Override
    public RutinaDTO getRutinaById(Long id) {
        Rutina rutina = rutinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina", "id", id));
        return toRutinaDTO(rutina);
    }

    @Override
    public List<RutinaDTO> getAllRutinas() {
        return rutinaRepository.findAll().stream()
                .map(this::toRutinaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RutinaDTO> getRutinasByPacienteId(Long pacienteId) {
        // Validar que el paciente exista
        PacienteDto paciente = externalServiceClient.obtenerPaciente(pacienteId);
        if (paciente == null) {
            throw new ResourceNotFoundException("Paciente", "id", pacienteId);
        }
        return rutinaRepository.findByPacienteId(pacienteId).stream()
                .map(this::toRutinaDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RutinaDTO updateRutina(Long id, RutinaDTO rutinaDTO) {
        Rutina rutina = rutinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina", "id", id));

        // Actualizar campos básicos
        rutina.setNombre(rutinaDTO.getNombre());
        rutina.setFechaInicio(rutinaDTO.getFechaInicio());
        rutina.setFechaFin(rutinaDTO.getFechaFin());

        // Reemplazar lista de ejercicios
        rutina.getEjercicios().clear();
        if (rutinaDTO.getEjercicios() != null && !rutinaDTO.getEjercicios().isEmpty()) {
            List<EjercicioRutina> nuevosEjercicios = rutinaDTO.getEjercicios().stream()
                    .map(dto -> toEjercicioEntity(dto, rutina))
                    .collect(Collectors.toList());
            rutina.getEjercicios().addAll(nuevosEjercicios);
        }

        Rutina updated = rutinaRepository.save(rutina);
        return toRutinaDTO(updated);
    }

    @Override
    @Transactional
    public void deleteRutina(Long id) {
        Rutina rutina = rutinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina", "id", id));
        rutinaRepository.delete(rutina);
    }
}
