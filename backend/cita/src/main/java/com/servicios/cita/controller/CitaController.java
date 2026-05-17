package com.servicios.cita.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servicios.cita.model.Cita;
import com.servicios.cita.service.CitaService;

@RestController
@RequestMapping("api/v1/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> listarCita() {
        List<Cita> citas = citaService.mostrarCitas();
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/rango")
    public ResponseEntity<List<Cita>> buscarCitasPorRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        
        List<Cita> citas = citaService.buscarPorRangoFechas(inicio, fin);
        
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        
        return ResponseEntity.ok(citas);
    }

    @PostMapping
    public ResponseEntity<Cita> guardarCita(@RequestBody Cita unaCita) {
        Cita citaNueva = citaService.crearCita(unaCita);
        return ResponseEntity.status(HttpStatus.CREATED).body(citaNueva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscarCita(@PathVariable Long id) {
        try {
            Cita cita = citaService.buscarId(id);
            return ResponseEntity.ok(cita);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> modificarCita(@PathVariable Long id, @RequestBody Cita unaCita) {
        try {
            Cita citaExistente = citaService.buscarId(id);
            
            // Actualizar campos
            citaExistente.setIdPaciente(unaCita.getIdPaciente());
            citaExistente.setIdNutricionista(unaCita.getIdNutricionista());
            citaExistente.setFechaHoraInicio(unaCita.getFechaHoraInicio());
            citaExistente.setMotivo(unaCita.getMotivo());
            citaExistente.setEstado(unaCita.getEstado());

            Cita actualizada = citaService.crearCita(citaExistente);
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public String eliminarCita(@PathVariable Long id) {
        try {
            citaService.eliminarCita(id);
            return "Cita eliminada";
        } catch (Exception e) {
            return "La cita no existe";
        }
    }
}
