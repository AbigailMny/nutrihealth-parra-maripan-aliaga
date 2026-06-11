package com.servicio.paciente.controller;

import com.servicio.paciente.dto.PacienteDTO;
import com.servicio.paciente.service.PacienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@Tag(name = "Pacientes", description = "Operaciones relacionadas con la gestión de pacientes") // Documentación Swagger
@CrossOrigin(origins = "*") // Permite el consumo desde el Gateway/Swagger
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Obtener todos los pacientes", description = "Retorna la lista completa de pacientes registrados")
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAllPacientes() {
        return ResponseEntity.ok(pacienteService.findAll());
    }

    @Operation(summary = "Buscar pacientes por nombre y apellido", description = "Retorna una lista de pacientes filtrados por nombre y/o apellido")
    @GetMapping("/buscar")
    public ResponseEntity<List<PacienteDTO>> buscarPacientes(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido) {
        if ((nombre == null || nombre.isBlank()) && (apellido == null || apellido.isBlank())) {
            return ResponseEntity.badRequest().build();
        }
        List<PacienteDTO> pacientes = pacienteService.searchByNombreApellido(nombre, apellido);
        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);
    }

    @Operation(summary = "Obtener un paciente por ID", description = "Retorna un paciente por su identificador único")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.findById(id));
    }

    @Operation(summary = "Crear un nuevo paciente", description = "Registra un nuevo paciente")
    @PostMapping
    public ResponseEntity<PacienteDTO> createPaciente(@RequestBody PacienteDTO pacienteDTO) {
        return new ResponseEntity<>(pacienteService.save(pacienteDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un paciente", description = "Modifica los datos de un paciente existente")
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> updatePaciente(@PathVariable Long id,
            @RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.update(id, pacienteDTO));
    }

    @Operation(summary = "Eliminar un paciente", description = "Elimina un paciente por su identificador único")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
