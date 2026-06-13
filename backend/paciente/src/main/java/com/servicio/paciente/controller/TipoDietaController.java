package com.servicio.paciente.controller;

import com.servicio.paciente.dto.TipoDietaDTO;
import com.servicio.paciente.exception.ErrorResponse;
import com.servicio.paciente.service.TipoDietaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-dieta")
public class TipoDietaController {

    @Autowired
    private TipoDietaService tipoDietaService;

    @GetMapping
    public ResponseEntity<List<TipoDietaDTO>> getAllTiposDieta() {
        return ResponseEntity.ok(tipoDietaService.findAll());
    }

    @Operation(summary = "Obtener tipo de dieta por ID", description = "Retorna una dieta específica según su ID único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de dieta obtenido con éxito"),
        @ApiResponse(responseCode = "404", description = "El tipo de dieta solicitado no existe", 
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TipoDietaDTO> getTipoDietaById(@PathVariable Long id) {
        return ResponseEntity.ok(tipoDietaService.findById(id));
    }

    @Operation(summary = "Crear tipo de dieta", description = "Registra una nueva categoría de dieta (ej: Vegana, Keto)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tipo de dieta registrado con éxito"),
        @ApiResponse(responseCode = "400", description = "El formato o los campos obligatorios del JSON son incorrectos")
    })
    @PostMapping
    public ResponseEntity<TipoDietaDTO> createTipoDieta(@Valid @RequestBody TipoDietaDTO tipoDietaDTO) {
        return new ResponseEntity<>(tipoDietaService.save(tipoDietaDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar tipo de dieta", description = "Modifica los datos de una dieta existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de dieta modificado con éxito"),
        @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos"),
        @ApiResponse(responseCode = "404", description = "El tipo de dieta a modificar no fue encontrado", 
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<TipoDietaDTO> updateTipoDieta(@PathVariable Long id,
            @Valid @RequestBody TipoDietaDTO tipoDietaDTO) {
        return ResponseEntity.ok(tipoDietaService.update(id, tipoDietaDTO));
    }

    @Operation(summary = "Eliminar tipo de dieta", description = "Elimina de forma física una dieta del catálogo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tipo de dieta eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "El recurso que intenta eliminar no existe", 
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoDieta(@PathVariable Long id) {
        tipoDietaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
