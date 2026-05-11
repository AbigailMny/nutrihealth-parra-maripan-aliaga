package com.recetas.api_recetas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.api_recetas.model.RecetaIngredientes;
import com.recetas.api_recetas.service.IngredientesService;

@RestController
@RequestMapping("api/v1/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredientesService ingredientesService;

    @GetMapping
    public ResponseEntity <List<RecetaIngredientes>> listarIngredientes(){
        List<RecetaIngredientes> ingredientes = ingredientesService.mostrarIngredientes();
        if(ingredientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ingredientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaIngredientes> buscarIngrediente(@PathVariable Long id){
        try{
            RecetaIngredientes ingredientes = ingredientesService.buscarId(id);
            return ResponseEntity.ok(ingredientes);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarIngredientes(@RequestBody RecetaIngredientes unIng){
        try{
            return ResponseEntity.ok(ingredientesService.crearIngredientes(unIng));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaIngredientes> modificarIngrediente(@PathVariable Long id, @RequestBody RecetaIngredientes ingr){
        try{
            RecetaIngredientes ing = ingredientesService.buscarId(id);
            ing.setId_ingredientes(id);
            ing.setCantidadGramos(ingr.getCantidadGramos());

            ingredientesService.crearIngredientes(ingr);
            return ResponseEntity.ok(ingr);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }    
    }

    @DeleteMapping("/{id}")
    public String eliminarReceta(@PathVariable Long id){
        try{
            ingredientesService.eliminarIngrediente(id);;
            return "Receta eliminada";
        }catch (Exception e){
            return "La receta no existe";
        }
    }

}
