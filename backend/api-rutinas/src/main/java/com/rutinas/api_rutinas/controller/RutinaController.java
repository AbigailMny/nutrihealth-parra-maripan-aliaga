package com.rutinas.api_rutinas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rutinas.api_rutinas.dto.RutinaDTO;
import com.rutinas.api_rutinas.exception.ErrorResponse;
import com.rutinas.api_rutinas.service.RutinaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/rutinas")
@Tag(name = "Rutinas", description = "Operaciones relacionadas con la gestión de rutinas de ejercicio")
@CrossOrigin(origins = "*")
public class RutinaController {

    @Autowired
    private RutinaService rutinaService;

    // ===== RUTINAS =====

    @Operation(summary = "Obtener todas las rutinas", description = "Retorna la lista completa de rutinas registradas")
    @GetMapping
    public ResponseEntity<List<RutinaDTO>> getAllRutinas() {
        return ResponseEntity.ok(rutinaService.getAllRutinas());
    }

    @Operation(summary = "Obtener una rutina por ID", description = "Retorna una rutina por su identificador único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rutina encontrada"),
        @ApiResponse(responseCode = "404", description = "La rutina con el ID proporcionado no existe",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<RutinaDTO> getRutinaById(@PathVariable Long id) {
        return ResponseEntity.ok(rutinaService.getRutinaById(id));
    }

    @Operation(summary = "Obtener rutinas por paciente", description = "Retorna todas las rutinas asignadas a un paciente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rutinas encontradas"),
        @ApiResponse(responseCode = "404", description = "El paciente con el ID proporcionado no existe",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<RutinaDTO>> getRutinasByPacienteId(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(rutinaService.getRutinasByPacienteId(pacienteId));
    }

    @Operation(summary = "Crear una nueva rutina", description = "Registra una nueva rutina con sus ejercicios para un paciente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Rutina creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de la rutina inválidos"),
        @ApiResponse(responseCode = "404", description = "El paciente especificado no existe",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<RutinaDTO> createRutina(@Valid @RequestBody RutinaDTO rutinaDTO) {
        return new ResponseEntity<>(rutinaService.createRutina(rutinaDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una rutina", description = "Modifica los datos y ejercicios de una rutina existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rutina actualizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos"),
        @ApiResponse(responseCode = "404", description = "No se encontró la rutina que desea modificar",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<RutinaDTO> updateRutina(@PathVariable Long id,
            @Valid @RequestBody RutinaDTO rutinaDTO) {
        return ResponseEntity.ok(rutinaService.updateRutina(id, rutinaDTO));
    }

    @Operation(summary = "Eliminar una rutina", description = "Elimina una rutina y todos sus ejercicios asociados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Rutina eliminada con éxito"),
        @ApiResponse(responseCode = "404", description = "La rutina a eliminar no existe",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRutina(@PathVariable Long id) {
        rutinaService.deleteRutina(id);
        return ResponseEntity.noContent().build();
    }
}
