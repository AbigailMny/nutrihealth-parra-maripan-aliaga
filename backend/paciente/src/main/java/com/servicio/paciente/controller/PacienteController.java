package com.servicio.paciente.controller;

import com.servicio.paciente.dto.PacienteDTO;
import com.servicio.paciente.service.PacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAllPacientes() {
        return ResponseEntity.ok(pacienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> createPaciente(@RequestBody PacienteDTO pacienteDTO) {
        return new ResponseEntity<>(pacienteService.save(pacienteDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> updatePaciente(@PathVariable Long id,
            @RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.update(id, pacienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
