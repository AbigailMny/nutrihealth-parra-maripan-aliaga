package com.servicio.nutricionista.controller;

import com.servicio.nutricionista.dto.NutricionistaDTO;
import com.servicio.nutricionista.service.NutricionistaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nutricionistas")
@Tag(name = "Nutricionistas", description = "Operaciones relacionadas con la gestión de las nutricionistas")
@CrossOrigin(origins = "*") // Permite el consumo desde el Gateway/Swagger
public class NutricionistaController {

    @Autowired
    private NutricionistaService nutricionistaService;

    @Operation(summary = "Obtener todas las nutricionistas", description = "Devuelve una lista de todas las nutricionistas registradas en el sistema")
    @GetMapping
    public ResponseEntity<List<NutricionistaDTO>> getAllNutricionistas() {
        return ResponseEntity.ok(nutricionistaService.findAll());
    }

    @Operation(summary = "Obtener una nutricionista por ID", description = "Devuelve los detalles de una nutricionista específica utilizando su ID")
    @GetMapping("/{id}")
    public ResponseEntity<NutricionistaDTO> getNutricionistaById(@PathVariable Long id) {
        return ResponseEntity.ok(nutricionistaService.findById(id));
    }

    @Operation(summary = "Crear una nueva nutricionista", description = "Crea una nueva nutricionista en el sistema")
    @PostMapping
    public ResponseEntity<NutricionistaDTO> createNutricionista(@RequestBody NutricionistaDTO nutricionistaDTO) {
        return new ResponseEntity<>(nutricionistaService.save(nutricionistaDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una nutricionista", description = "Actualiza los detalles de una nutricionista específica utilizando su ID")
    @PutMapping("/{id}")
    public ResponseEntity<NutricionistaDTO> updateNutricionista(@PathVariable Long id,
            @RequestBody NutricionistaDTO nutricionistaDTO) {
        return ResponseEntity.ok(nutricionistaService.update(id, nutricionistaDTO));
    }

    @Operation(summary = "Eliminar una nutricionista", description = "Elimina una nutricionista específica utilizando su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutricionista(@PathVariable Long id) {
        nutricionistaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
