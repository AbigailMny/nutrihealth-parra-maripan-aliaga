package com.antecedentes.api_antecedentes.controller;

import com.antecedentes.api_antecedentes.dto.AlergiaDTO;
import com.antecedentes.api_antecedentes.service.AlergiaService;
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
@RequestMapping("/api/v1/alergias")
@RequiredArgsConstructor
@Tag(name = "Alergias", description = "API para la gestión de alergias")
public class AlergiaController {

    private final AlergiaService alergiaService;

    @GetMapping
    @Operation(summary = "Obtener todas las alergias")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de alergias obtenida con éxito")
    })
    public ResponseEntity<List<AlergiaDTO>> getAllAlergias() {
        return ResponseEntity.ok(alergiaService.getAllAlergias());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una alergia por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Alergia encontrada"),
        @ApiResponse(responseCode = "404", description = "La alergia solicitada no existe",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<AlergiaDTO> getAlergiaById(@PathVariable Long id) {
        return ResponseEntity.ok(alergiaService.getAlergiaById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva alergia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Alergia creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Los datos del JSON enviados no cumplen con las validaciones requeridas",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<AlergiaDTO> createAlergia(@Valid @RequestBody AlergiaDTO alergiaDTO) {
        return new ResponseEntity<>(alergiaService.createAlergia(alergiaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una alergia existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Alergia actualizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "No se encontró la alergia que desea modificar",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<AlergiaDTO> updateAlergia(@PathVariable Long id, @Valid @RequestBody AlergiaDTO alergiaDTO) {
        return ResponseEntity.ok(alergiaService.updateAlergia(id, alergiaDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una alergia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Alergia eliminada con éxito"),
        @ApiResponse(responseCode = "404", description = "La alergia a eliminar no existe",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> deleteAlergia(@PathVariable Long id) {
        alergiaService.deleteAlergia(id);
        return ResponseEntity.noContent().build();
    }
}
