package com.mediciones.api_mediciones.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mediciones.api_mediciones.service.MedicionService;
import com.mediciones.api_mediciones.dto.MedicionRequest;
import com.mediciones.api_mediciones.dto.MedicionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/mediciones")
@RequiredArgsConstructor
@Tag(name = "Mediciones", description = "API para la gestión de mediciones")
public class MedicionesController 
{
        private final MedicionService medicionService;

        @Operation(summary = "Crear una nueva medición")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Medición creada exitosamente"),
                @ApiResponse(responseCode = "400", description = "Datos de la medición inválidos")
        })
        @PostMapping
        public ResponseEntity<MedicionResponse> createMedicion(
                @Valid @RequestBody MedicionRequest request) {

        return new ResponseEntity<>(
                medicionService.createMedicion(request),
                HttpStatus.CREATED);
        }

        @Operation(summary = "Obtener una medición por su ID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Medición encontrada"),
                @ApiResponse(responseCode = "404", description = "La medición no existe")
        })
        @GetMapping("/{id}")
        public ResponseEntity<MedicionResponse> getMedicionById(
                @PathVariable Long id) {

        return ResponseEntity.ok(
                medicionService.getMedicionById(id));
        }

        @Operation(summary = "Obtener todas las mediciones de un paciente")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Mediciones encontradas"),
                @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
        })
        @GetMapping("/paciente/{pacienteId}")
        public ResponseEntity<List<MedicionResponse>> getMedicionesByPacienteId(
                @PathVariable Long pacienteId) {

        return ResponseEntity.ok(
                medicionService.getMedicionesByPacienteId(pacienteId));
        }

        @Operation(summary = "Obtener todas las mediciones")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Mediciones encontradas")
        })
        @GetMapping
        public ResponseEntity<List<MedicionResponse>> getAllMediciones() {
            return ResponseEntity.ok(medicionService.getAllMediciones());
        }

        @Operation(summary = "Actualizar una medición existente")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Medición actualizada exitosamente"),
                @ApiResponse(responseCode = "404", description = "La medición no existe")
        })
        @PutMapping("/{id}")
        public ResponseEntity<MedicionResponse> updateMedicion(
                @PathVariable Long id,
                @Valid @RequestBody MedicionRequest request) {

        return ResponseEntity.ok(
                medicionService.updateMedicion(id, request));
        }

        @Operation(summary = "Eliminar una medición")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "204", description = "Medición eliminada exitosamente"),
                @ApiResponse(responseCode = "404", description = "La medición no existe")
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteMedicion(
                @PathVariable Long id) {

        medicionService.deleteMedicion(id);
        return ResponseEntity.noContent().build();
        }
}


