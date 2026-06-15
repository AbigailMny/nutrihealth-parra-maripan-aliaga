package com.antecedentes.api_antecedentes.service.impl;

import com.antecedentes.api_antecedentes.dto.*;
import com.antecedentes.api_antecedentes.exception.ResourceNotFoundException;
import com.antecedentes.api_antecedentes.model.*;
import com.antecedentes.api_antecedentes.repository.*;
import com.antecedentes.api_antecedentes.service.AntecedentesClinicosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AntecedentesClinicosServiceImpl implements AntecedentesClinicosService {

        private final AntecedentesClinicosRepository antecedentesRepository;
        private final EnfermedadRepository enfermedadRepository;
        private final AlergiaRepository alergiaRepository;
        private final MedicamentoRepository medicamentoRepository;
        private final com.antecedentes.api_antecedentes.client.ExternalServiceClient externalServiceClient;

    private AntecedentesClinicosDTO mapToDTO(AntecedentesClinicos antecedente) {
        Set<EnfermedadDTO> enfermedades = antecedente.getEnfermedades().stream()
                .map(e -> EnfermedadDTO.builder().id(e.getId()).nombre(e.getNombre()).descripcion(e.getDescripcion()).build())
                .collect(Collectors.toSet());

        Set<AlergiaDTO> alergias = antecedente.getAlergias().stream()
                .map(a -> AlergiaDTO.builder().id(a.getId()).nombre(a.getNombre()).descripcion(a.getDescripcion()).build())
                .collect(Collectors.toSet());

        Set<AntecedenteMedicamentoDTO> medicamentos = antecedente.getMedicamentos().stream()
                .map(m -> AntecedenteMedicamentoDTO.builder()
                        .medicamentoId(m.getMedicamento().getId())
                        .nombreMedicamento(m.getMedicamento().getNombre())
                        .dosis(m.getDosis())
                        .build())
                .collect(Collectors.toSet());

        return AntecedentesClinicosDTO.builder()
                .id(antecedente.getId())
                .pacienteId(antecedente.getPacienteId())
                .tipoSangre(antecedente.getTipoSangre())
                .observacionesGenerales(antecedente.getObservacionesGenerales())
                .enfermedades(enfermedades)
                .alergias(alergias)
                .medicamentos(medicamentos)
                .paciente(externalServiceClient.obtenerPaciente(antecedente.getPacienteId()))
                .build();
    }

    @Override
    public List<AntecedentesClinicosDTO> getAllAntecedentes() {
        return antecedentesRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AntecedentesClinicosDTO getAntecedentesById(Long id) {
        AntecedentesClinicos antecedente = antecedentesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AntecedentesClinicos", "id", id));
        return mapToDTO(antecedente);
    }

    @Override
    public AntecedentesClinicosDTO getAntecedentesByPacienteId(Long pacienteId) {
        AntecedentesClinicos antecedente = antecedentesRepository.findByPacienteId(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("AntecedentesClinicos", "pacienteId", pacienteId));
        return mapToDTO(antecedente);
    }

    @Override
    @Transactional
    public AntecedentesClinicosDTO saveAntecedentes(AntecedentesClinicosRequest request) {
        AntecedentesClinicos antecedente = antecedentesRepository.findByPacienteId(request.getPacienteId())
                .orElse(new AntecedentesClinicos());

        antecedente.setPacienteId(request.getPacienteId());
        antecedente.setTipoSangre(request.getTipoSangre());
        antecedente.setObservacionesGenerales(request.getObservacionesGenerales());

        // Update enfermedades
        Set<Enfermedad> enfermedades = new HashSet<>();
        if (request.getEnfermedadIds() != null) {
            for (Long enfId : request.getEnfermedadIds()) {
                Enfermedad enf = enfermedadRepository.findById(enfId)
                        .orElseThrow(() -> new ResourceNotFoundException("Enfermedad", "id", enfId));
                enfermedades.add(enf);
            }
        }
        antecedente.setEnfermedades(enfermedades);

        // Update alergias
        Set<Alergia> alergias = new HashSet<>();
        if (request.getAlergiaIds() != null) {
            for (Long alId : request.getAlergiaIds()) {
                Alergia al = alergiaRepository.findById(alId)
                        .orElseThrow(() -> new ResourceNotFoundException("Alergia", "id", alId));
                alergias.add(al);
            }
        }
        antecedente.setAlergias(alergias);

        // Guardar primero para poder usar el ID en la relación
        AntecedentesClinicos savedAntecedente = antecedentesRepository.save(antecedente);

        // Update medicamentos (limpiando y recreando relaciones para simplicidad)
        savedAntecedente.getMedicamentos().clear();
        if (request.getMedicamentos() != null) {
            for (AntecedenteMedicamentoDTO medDTO : request.getMedicamentos()) {
                Medicamento med = medicamentoRepository.findById(medDTO.getMedicamentoId())
                        .orElseThrow(() -> new ResourceNotFoundException("Medicamento", "id", medDTO.getMedicamentoId()));
                
                AntecedenteMedicamento am = AntecedenteMedicamento.builder()
                        .id(new AntecedenteMedicamentoId(savedAntecedente.getId(), med.getId()))
                        .antecedente(savedAntecedente)
                        .medicamento(med)
                        .dosis(medDTO.getDosis())
                        .build();
                // el ID embebido se asignará gracias a @MapsId y los setters
                savedAntecedente.getMedicamentos().add(am);
            }
        }

        return mapToDTO(antecedentesRepository.save(savedAntecedente));
    }

    @Override
    public void deleteAntecedentes(Long id) {
        AntecedentesClinicos antecedente = antecedentesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AntecedentesClinicos", "id", id));
        antecedentesRepository.delete(antecedente);
    }
}
