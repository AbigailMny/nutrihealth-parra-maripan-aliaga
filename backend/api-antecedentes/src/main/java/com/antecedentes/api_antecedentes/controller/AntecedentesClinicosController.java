package com.antecedentes.api_antecedentes.controller;

import com.antecedentes.api_antecedentes.dto.AntecedentesClinicosDTO;
import com.antecedentes.api_antecedentes.dto.AntecedentesClinicosRequest;
import com.antecedentes.api_antecedentes.service.AntecedentesClinicosService;
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
@RequestMapping("/api/v1/antecedentes")
@RequiredArgsConstructor
@Tag(name = "Antecedentes Clínicos", description = "API para la gestión de antecedentes clínicos de pacientes")
public class AntecedentesClinicosController {

    private final AntecedentesClinicosService antecedentesService;

    @GetMapping
    @Operation(summary = "Obtener todos los antecedentes clínicos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de antecedentes obtenida con éxito")
    })
    public ResponseEntity<List<AntecedentesClinicosDTO>> getAllAntecedentes() {
        return ResponseEntity.ok(antecedentesService.getAllAntecedentes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un antecedente clínico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Antecedente clínico encontrado"),
        @ApiResponse(responseCode = "404", description = "El antecedente solicitado no existe",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<AntecedentesClinicosDTO> getAntecedentesById(@PathVariable Long id) {
        return ResponseEntity.ok(antecedentesService.getAntecedentesById(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    @Operation(summary = "Obtener un antecedente clínico por ID del paciente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Antecedente clínico encontrado para el paciente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron antecedentes para el paciente solicitado",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<AntecedentesClinicosDTO> getAntecedentesByPacienteId(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(antecedentesService.getAntecedentesByPacienteId(pacienteId));
    }

    @PostMapping
    @Operation(summary = "Crear o actualizar antecedentes clínicos para un paciente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Antecedente clínico guardado/actualizado con éxito"),
        @ApiResponse(responseCode = "400", description = "Los datos del JSON enviados no cumplen con las validaciones requeridas",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "No se encontró alguna enfermedad, alergia o medicamento proporcionado",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<AntecedentesClinicosDTO> saveAntecedentes(@RequestBody @Valid AntecedentesClinicosRequest request) {
        return new ResponseEntity<>(antecedentesService.saveAntecedentes(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un antecedente clínico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Antecedente clínico eliminado con éxito"),
        @ApiResponse(responseCode = "404", description = "El antecedente clínico a eliminar no existe",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> deleteAntecedentes(@PathVariable Long id) {
        antecedentesService.deleteAntecedentes(id);
        return ResponseEntity.noContent().build();
    }
}
