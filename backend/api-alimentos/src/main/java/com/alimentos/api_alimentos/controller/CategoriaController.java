package com.alimentos.api_alimentos.controller;

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
import com.alimentos.api_alimentos.model.Categoria;
import com.alimentos.api_alimentos.service.CategoriaService;

@RestController
@RequestMapping("api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity <List<Categoria>> listarCategorias(){
        List <Categoria> categoria = categoriaService.mostrarCategorias();
        if(categoria.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria){
        Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable Long id){
        try{
            Categoria categoria = categoriaService.buscarId(id);
            return ResponseEntity.ok(categoria);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> modificarCategoria(@PathVariable Long id, @RequestBody Categoria categoria){
        try{
            Categoria cat = categoriaService.buscarId(id);
            cat.setId_categoria(id);
            cat.setNombre(categoria.getNombre());

            categoriaService.crearCategoria(categoria);
            return ResponseEntity.ok(categoria);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public String eliminarCategoria(@PathVariable Long id){
        try{
            categoriaService.eliminarCategoria(id);
            return "La categoria del alimento ha sido eliminado";
        }catch (Exception e){
            return "La categoria del alimento no existe";
        }
    }

}
