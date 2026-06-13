package com.servicio.paciente.controller;

import com.servicio.paciente.dto.PacienteDTO;
import com.servicio.paciente.exception.ErrorResponse;
import com.servicio.paciente.service.PacienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda procesada con éxito"),
        @ApiResponse(responseCode = "400", description = "Debe proporcionar al menos un parámetro de búsqueda (nombre o apellido)")
    })
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
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
        @ApiResponse(responseCode = "404", description = "El paciente con el ID proporcionado no existe", 
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id) {
        // Nota: Asumimos que tu servicio lanza una RuntimeException si el id no existe
        return ResponseEntity.ok(pacienteService.findById(id));
    }

    @Operation(summary = "Crear un nuevo paciente", description = "Registra un nuevo paciente en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Paciente creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Los datos del JSON enviados no cumplen con las validaciones requeridas")
    })
    @PostMapping
    public ResponseEntity<PacienteDTO> createPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        return new ResponseEntity<>(pacienteService.save(pacienteDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un paciente", description = "Modifica los datos de un paciente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos"),
        @ApiResponse(responseCode = "404", description = "No se encontró el paciente que desea modificar", 
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> updatePaciente(@PathVariable Long id,
            @Valid @RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.update(id, pacienteDTO));
    }

    @Operation(summary = "Eliminar un paciente", description = "Elimina un paciente por su identificador único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Paciente eliminado con éxito"),
        @ApiResponse(responseCode = "404", description = "El paciente a eliminar no existe", 
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
