package com.minutas.api_minutas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.minutas.api_minutas.dto.MinutaDto;
import com.minutas.api_minutas.service.MinutaService;

@RestController
@RequestMapping("api/v1/minutas")
public class MinutaController {

    @Autowired
    private MinutaService minutaService;

    @GetMapping
    public ResponseEntity<List<MinutaDto>> listarMinutas() {
        List<MinutaDto> minutas = minutaService.mostrarMinutas();
        if (minutas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(minutas);
    }

    @PostMapping
    public ResponseEntity<MinutaDto> guardarMinuta(@RequestBody MinutaDto unaMinuta) {
        MinutaDto minutaNueva = minutaService.crearMinuta(unaMinuta);
        return ResponseEntity.status(HttpStatus.CREATED).body(minutaNueva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MinutaDto> buscarMinuta(@PathVariable Long id) {
        try {
            MinutaDto minuta = minutaService.buscarId(id);
            return ResponseEntity.ok(minuta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MinutaDto> modificarMinuta(@PathVariable Long id, @RequestBody MinutaDto unaMinuta) {
        try {
            MinutaDto minutaGuardada = minutaService.actualizarMinuta(id, unaMinuta);
            return ResponseEntity.ok(minutaGuardada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public String eliminarMinuta(@PathVariable Long id) {
        try {
            minutaService.eliminarMinuta(id);
            return "Minuta eliminada";
        } catch (Exception e) {
            return "La minuta no existe";
        }
    }
}

