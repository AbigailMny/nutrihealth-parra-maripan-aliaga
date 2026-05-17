package com.servicio.paciente.service.impl;

import com.servicio.paciente.dto.PacienteDTO;
import com.servicio.paciente.dto.TipoDietaDTO;
import com.servicio.paciente.model.Paciente;
import com.servicio.paciente.model.TipoDieta;
import com.servicio.paciente.repository.PacienteRepository;
import com.servicio.paciente.repository.TipoDietaRepository;
import com.servicio.paciente.service.PacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TipoDietaRepository tipoDietaRepository;

    @Override
    public List<PacienteDTO> findAll() {
        return pacienteRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PacienteDTO> searchByNombreApellido(String nombre, String apellido) {
        List<Paciente> pacientes;
        if (nombre != null && !nombre.isBlank() && apellido != null && !apellido.isBlank()) {
            pacientes = pacienteRepository.findByNombresContainingIgnoreCaseAndApellidosContainingIgnoreCase(nombre, apellido);
        } else if (nombre != null && !nombre.isBlank()) {
            pacientes = pacienteRepository.findByNombresContainingIgnoreCase(nombre);
        } else if (apellido != null && !apellido.isBlank()) {
            pacientes = pacienteRepository.findByApellidosContainingIgnoreCase(apellido);
        } else {
            return Collections.emptyList();
        }
        return pacientes.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public PacienteDTO findById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + id));
        return mapToDTO(paciente);
    }

    @Override
    public PacienteDTO save(PacienteDTO pacienteDTO) {
        Paciente paciente = mapToEntity(pacienteDTO);
        Paciente saved = pacienteRepository.save(paciente);
        return mapToDTO(saved);
    }

    @Override
    public PacienteDTO update(Long id, PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + id));

        paciente.setRun(pacienteDTO.getRun());
        paciente.setNombres(pacienteDTO.getNombres());
        paciente.setApellidos(pacienteDTO.getApellidos());
        paciente.setCorreo(pacienteDTO.getCorreo());
        paciente.setTelefono(pacienteDTO.getTelefono());

        if (pacienteDTO.getTipoDietaId() != null) {
            TipoDieta tipoDieta = tipoDietaRepository.findById(pacienteDTO.getTipoDietaId())
                    .orElseThrow(() -> new RuntimeException("Tipo de dieta no encontrado"));
            paciente.setTipoDieta(tipoDieta);
        } else {
            paciente.setTipoDieta(null);
        }

        Paciente updated = pacienteRepository.save(paciente);
        return mapToDTO(updated);
    }

    @Override
    public void delete(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + id));
        pacienteRepository.delete(paciente);
    }

    private PacienteDTO mapToDTO(Paciente paciente) {
        TipoDietaDTO tipoDietaDTO = null;
        if (paciente.getTipoDieta() != null) {
            tipoDietaDTO = TipoDietaDTO.builder()
                    .id(paciente.getTipoDieta().getId())
                    .nombre(paciente.getTipoDieta().getNombre())
                    .descripcion(paciente.getTipoDieta().getDescripcion())
                    .build();
        }

        return PacienteDTO.builder()
                .id(paciente.getId())
                .run(paciente.getRun())
                .nombres(paciente.getNombres())
                .apellidos(paciente.getApellidos())
                .correo(paciente.getCorreo())
                .telefono(paciente.getTelefono())
                .tipoDietaId(paciente.getTipoDieta() != null ? paciente.getTipoDieta().getId() : null)
                .tipoDieta(tipoDietaDTO)
                .fechaRegistro(paciente.getFechaRegistro())
                .build();
    }

    private Paciente mapToEntity(PacienteDTO dto) {
        TipoDieta tipoDieta = null;
        if (dto.getTipoDietaId() != null) {
            tipoDieta = tipoDietaRepository.findById(dto.getTipoDietaId())
                    .orElseThrow(() -> new RuntimeException("Tipo de dieta no encontrado"));
        }

        return Paciente.builder()
                .run(dto.getRun())
                .nombres(dto.getNombres())
                .apellidos(dto.getApellidos())
                .correo(dto.getCorreo())
                .telefono(dto.getTelefono())
                .tipoDieta(tipoDieta)
                .fechaRegistro(dto.getFechaRegistro() != null ? dto.getFechaRegistro() : java.time.LocalDate.now())
                .build();
    }
}
