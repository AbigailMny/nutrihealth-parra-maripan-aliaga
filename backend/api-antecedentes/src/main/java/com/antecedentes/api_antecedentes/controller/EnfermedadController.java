package com.antecedentes.api_antecedentes.controller;

import com.antecedentes.api_antecedentes.dto.EnfermedadDTO;
import com.antecedentes.api_antecedentes.service.EnfermedadService;
import com.antecedentes.api_antecedentes.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enfermedades")
@RequiredArgsConstructor
@Tag(name = "Enfermedades", description = "API para la gestión de enfermedades")
public class EnfermedadController {

    private final EnfermedadService enfermedadService;

    @GetMapping
    @Operation(summary = "Obtener todas las enfermedades")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de enfermedades obtenida con éxito")
    })
    public ResponseEntity<List<EnfermedadDTO>> getAllEnfermedades() {
        return ResponseEntity.ok(enfermedadService.getAllEnfermedades());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una enfermedad por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Enfermedad encontrada"),
        @ApiResponse(responseCode = "404", description = "La enfermedad solicitada no existe",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<EnfermedadDTO> getEnfermedadById(@PathVariable Long id) {
        return ResponseEntity.ok(enfermedadService.getEnfermedadById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva enfermedad")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Enfermedad creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Los datos del JSON enviados no cumplen con las validaciones requeridas",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<EnfermedadDTO> createEnfermedad(@Valid @RequestBody EnfermedadDTO enfermedadDTO) {
        return new ResponseEntity<>(enfermedadService.createEnfermedad(enfermedadDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una enfermedad existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Enfermedad actualizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "No se encontró la enfermedad que desea modificar",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<EnfermedadDTO> updateEnfermedad(@PathVariable Long id,
            @Valid @RequestBody EnfermedadDTO enfermedadDTO) {
        return ResponseEntity.ok(enfermedadService.updateEnfermedad(id, enfermedadDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una enfermedad")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Enfermedad eliminada con éxito"),
        @ApiResponse(responseCode = "404", description = "La enfermedad a eliminar no existe",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> deleteEnfermedad(@PathVariable Long id) {
        enfermedadService.deleteEnfermedad(id);
        return ResponseEntity.noContent().build();
    }
}
