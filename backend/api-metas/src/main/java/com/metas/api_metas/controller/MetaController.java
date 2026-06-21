package com.metas.api_metas.controller;

import com.metas.api_metas.dto.MetaRequest;
import com.metas.api_metas.dto.MetaResponse;
import com.metas.api_metas.service.MetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.metas.api_metas.exception.ErrorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metas")
@RequiredArgsConstructor
@Tag(name = "Metas", description = "API para la gestión de metas de pacientes")
public class MetaController {

    private final MetaService metaService;

    @Operation(summary = "Crear una nueva meta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Meta creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de la meta inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<MetaResponse> createMeta(@Valid @RequestBody MetaRequest request) {
        return new ResponseEntity<>(metaService.createMeta(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener una meta por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Meta encontrada"),
        @ApiResponse(responseCode = "404", description = "La meta con el ID proporcionado no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<MetaResponse> getMetaById(@PathVariable Long id) {
        return ResponseEntity.ok(metaService.getMetaById(id));
    }

    @Operation(summary = "Obtener todas las metas de un paciente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Metas encontradas"),
        @ApiResponse(responseCode = "404", description = "El paciente con el ID proporcionado no existe o no tiene metas", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<MetaResponse>> getMetasByPacienteId(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(metaService.getMetasByPacienteId(pacienteId));
    }

    @Operation(summary = "Obtener todas las metas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Metas encontradas")
    })
    @GetMapping
    public ResponseEntity<List<MetaResponse>> getAllMetas() {
        return ResponseEntity.ok(metaService.getAllMetas());
    }

    @Operation(summary = "Actualizar una meta existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Meta actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "La meta con el ID proporcionado no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<MetaResponse> updateMeta(
            @PathVariable Long id, 
            @Valid @RequestBody MetaRequest request) {
        return ResponseEntity.ok(metaService.updateMeta(id, request));
    }

    @Operation(summary = "Eliminar una meta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Meta eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "La meta con el ID proporcionado no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeta(@PathVariable Long id) {
        metaService.deleteMeta(id);
        return ResponseEntity.noContent().build();
    }
}
