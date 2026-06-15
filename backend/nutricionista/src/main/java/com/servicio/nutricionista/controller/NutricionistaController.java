package com.servicio.nutricionista.controller;

import com.servicio.nutricionista.dto.NutricionistaDTO;
import com.servicio.nutricionista.exception.ErrorResponse;
import com.servicio.nutricionista.service.NutricionistaService;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nutricionistas")
@Tag(name = "Nutricionistas", description = "Operaciones relacionadas con la gestión de las nutricionistas")
@CrossOrigin(origins = "*")
public class NutricionistaController {

    @Autowired
    private NutricionistaService nutricionistaService;

    @Operation(summary = "Obtener todas las nutricionistas", description = "Devuelve una lista de todas las nutricionistas registradas en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de nutricionistas obtenida con éxito")
    })
    @GetMapping
    public ResponseEntity<List<NutricionistaDTO>> getAllNutricionistas() {
        return ResponseEntity.ok(nutricionistaService.findAll());
    }

    @Operation(summary = "Obtener una nutricionista por ID", description = "Devuelve los detalles de una nutricionista específica utilizando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Nutricionista encontrada"),
        @ApiResponse(responseCode = "404", description = "La nutricionista con el ID proporcionado no existe",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<NutricionistaDTO> getNutricionistaById(@PathVariable Long id) {
        return ResponseEntity.ok(nutricionistaService.findById(id));
    }

    @Operation(summary = "Crear una nueva nutricionista", description = "Crea una nueva nutricionista en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Nutricionista creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Los datos del JSON enviados no cumplen con las validaciones requeridas")
    })
    @PostMapping
    public ResponseEntity<NutricionistaDTO> createNutricionista(@Valid @RequestBody NutricionistaDTO nutricionistaDTO) {
        return new ResponseEntity<>(nutricionistaService.save(nutricionistaDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una nutricionista", description = "Actualiza los detalles de una nutricionista específica utilizando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Nutricionista actualizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos"),
        @ApiResponse(responseCode = "404", description = "No se encontró la nutricionista que desea modificar",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<NutricionistaDTO> updateNutricionista(@PathVariable Long id,
            @Valid @RequestBody NutricionistaDTO nutricionistaDTO) {
        return ResponseEntity.ok(nutricionistaService.update(id, nutricionistaDTO));
    }

    @Operation(summary = "Eliminar una nutricionista", description = "Elimina una nutricionista específica utilizando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Nutricionista eliminada con éxito"),
        @ApiResponse(responseCode = "404", description = "La nutricionista a eliminar no existe",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutricionista(@PathVariable Long id) {
        nutricionistaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
