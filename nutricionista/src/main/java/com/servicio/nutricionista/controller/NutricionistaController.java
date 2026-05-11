package com.servicio.nutricionista.controller;

import com.servicio.nutricionista.dto.NutricionistaDTO;
import com.servicio.nutricionista.service.NutricionistaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutricionistas")
public class NutricionistaController {

    @Autowired
    private NutricionistaService nutricionistaService;

    @GetMapping
    public ResponseEntity<List<NutricionistaDTO>> getAllNutricionistas() {
        return ResponseEntity.ok(nutricionistaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NutricionistaDTO> getNutricionistaById(@PathVariable Long id) {
        return ResponseEntity.ok(nutricionistaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<NutricionistaDTO> createNutricionista(@RequestBody NutricionistaDTO nutricionistaDTO) {
        return new ResponseEntity<>(nutricionistaService.save(nutricionistaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NutricionistaDTO> updateNutricionista(@PathVariable Long id,
            @RequestBody NutricionistaDTO nutricionistaDTO) {
        return ResponseEntity.ok(nutricionistaService.update(id, nutricionistaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutricionista(@PathVariable Long id) {
        nutricionistaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
