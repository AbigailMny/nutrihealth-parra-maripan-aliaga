package com.recetas.api_recetas.controller;

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

import com.recetas.api_recetas.model.Receta;
import com.recetas.api_recetas.service.RecetaService;

@RestController
@RequestMapping("api/v1/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public ResponseEntity <List<Receta>> listarReceta(){
        List<Receta> recetas = recetaService.mostrarRecetas();
        if(recetas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recetas);
    }

    @PostMapping
    public ResponseEntity<Receta> guardarReceta(@RequestBody Receta unaReceta){
        Receta recetaNueva = recetaService.crearReceta(unaReceta);
        return ResponseEntity.status(HttpStatus.CREATED).body(recetaNueva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> buscarReceta(@PathVariable Long id){
        try{
            Receta receta = recetaService.buscarId(id);
            return ResponseEntity.ok(receta);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> modificarReceta(@PathVariable Long id, @RequestBody Receta unaReceta){
        try{
            Receta rec = recetaService.buscarId(id);
            rec.setId_receta(id);
            rec.setNombre_plato(unaReceta.getNombre_plato());
            rec.setPreparacion(unaReceta.getPreparacion());
            rec.setEstado(unaReceta.getEstado());
            rec.setAnotaciones(unaReceta.getAnotaciones());

            recetaService.crearReceta(unaReceta);
            return ResponseEntity.ok(unaReceta);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }    
    }

    @DeleteMapping("/{id}")
    public String eliminarReceta(@PathVariable Long id){
        try{
            recetaService.eliminarReceta(id);
            return "Receta eliminada";
        }catch (Exception e){
            return "La receta no existe";
        }
    }


}
