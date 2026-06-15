package com.antecedentes.api_antecedentes.controller;

import com.antecedentes.api_antecedentes.dto.MedicamentoDTO;
import com.antecedentes.api_antecedentes.service.MedicamentoService;
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
@RequestMapping("/api/v1/medicamentos")
@RequiredArgsConstructor
@Tag(name = "Medicamentos", description = "API para la gestión de medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @GetMapping
    @Operation(summary = "Obtener todos los medicamentos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de medicamentos obtenida con éxito")
    })
    public ResponseEntity<List<MedicamentoDTO>> getAllMedicamentos() {
        return ResponseEntity.ok(medicamentoService.getAllMedicamentos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un medicamento por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Medicamento encontrado"),
        @ApiResponse(responseCode = "404", description = "El medicamento solicitado no existe",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<MedicamentoDTO> getMedicamentoById(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentoService.getMedicamentoById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo medicamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Medicamento creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Los datos del JSON enviados no cumplen con las validaciones requeridas",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<MedicamentoDTO> createMedicamento(@Valid @RequestBody MedicamentoDTO medicamentoDTO) {
        return new ResponseEntity<>(medicamentoService.createMedicamento(medicamentoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un medicamento existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Medicamento actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "No se encontró el medicamento que desea modificar",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<MedicamentoDTO> updateMedicamento(@PathVariable Long id,
            @Valid @RequestBody MedicamentoDTO medicamentoDTO) {
        return ResponseEntity.ok(medicamentoService.updateMedicamento(id, medicamentoDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un medicamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Medicamento eliminado con éxito"),
        @ApiResponse(responseCode = "404", description = "El medicamento a eliminar no existe",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> deleteMedicamento(@PathVariable Long id) {
        medicamentoService.deleteMedicamento(id);
        return ResponseEntity.noContent().build();
    }
}
