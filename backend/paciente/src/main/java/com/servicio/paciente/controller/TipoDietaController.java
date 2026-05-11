package com.servicio.paciente.controller;

import com.servicio.paciente.dto.TipoDietaDTO;
import com.servicio.paciente.service.TipoDietaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-dieta")
public class TipoDietaController {

    @Autowired
    private TipoDietaService tipoDietaService;

    @GetMapping
    public ResponseEntity<List<TipoDietaDTO>> getAllTiposDieta() {
        return ResponseEntity.ok(tipoDietaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDietaDTO> getTipoDietaById(@PathVariable Long id) {
        return ResponseEntity.ok(tipoDietaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TipoDietaDTO> createTipoDieta(@RequestBody TipoDietaDTO tipoDietaDTO) {
        return new ResponseEntity<>(tipoDietaService.save(tipoDietaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDietaDTO> updateTipoDieta(@PathVariable Long id,
            @RequestBody TipoDietaDTO tipoDietaDTO) {
        return ResponseEntity.ok(tipoDietaService.update(id, tipoDietaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoDieta(@PathVariable Long id) {
        tipoDietaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
