package com.metas.api_metas.controller;

import com.metas.api_metas.dto.ProgresoMetaRequest;
import com.metas.api_metas.dto.ProgresoMetaResponse;
import com.metas.api_metas.service.ProgresoMetaService;
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
@RequestMapping("/api/v1/progresos")
@RequiredArgsConstructor
@Tag(name = "Progresos de Metas", description = "API para el registro de avances en metas")
public class ProgresoMetaController {

    private final ProgresoMetaService progresoMetaService;

    @Operation(summary = "Registrar un nuevo progreso para una meta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Progreso registrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "La meta con el ID proporcionado no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos del progreso inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<ProgresoMetaResponse> addProgreso(@Valid @RequestBody ProgresoMetaRequest request) {
        return new ResponseEntity<>(progresoMetaService.addProgreso(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener los progresos de una meta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Progresos encontrados"),
        @ApiResponse(responseCode = "404", description = "La meta con el ID proporcionado no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/meta/{metaId}")
    public ResponseEntity<List<ProgresoMetaResponse>> getProgresosByMetaId(@PathVariable Long metaId) {
        return ResponseEntity.ok(progresoMetaService.getProgresosByMetaId(metaId));
    }

    @Operation(summary = "Eliminar un registro de progreso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Progreso eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "El progreso con el ID proporcionado no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgreso(@PathVariable Long id) {
        progresoMetaService.deleteProgreso(id);
        return ResponseEntity.noContent().build();
    }
}
