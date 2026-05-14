package com.servicios.cita.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicios.cita.client.ExternalServiceClient;
import com.servicios.cita.dto.NutricionistaDto;
import com.servicios.cita.dto.PacienteDto;
import com.servicios.cita.model.Cita;
import com.servicios.cita.repository.CitaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    private final ExternalServiceClient client;

    public CitaService(ExternalServiceClient client) {
        this.client = client;
    }

    public List<Cita> mostrarCitas() {
        return citaRepository.findAll();
    } 

    public Cita buscarId(Long id) {
        return citaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
    }

    public Cita crearCita(Cita cita) { 
        if (cita.getIdPaciente() == null || cita.getIdNutricionista() == null) {
            throw new RuntimeException("ID de Paciente y Nutricionista son obligatorios");
        }

        // Validar Paciente
        PacienteDto paciente = client.obtenerPaciente(cita.getIdPaciente());
        if (paciente == null) {
            throw new RuntimeException("Paciente no existe");
        }

        // Validar Nutricionista
        NutricionistaDto nutricionista = client.obtenerNutricionista(cita.getIdNutricionista());
        if (nutricionista == null) {
            throw new RuntimeException("Nutricionista no existe");
        }

        return citaRepository.save(cita);
    }

    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }
}
