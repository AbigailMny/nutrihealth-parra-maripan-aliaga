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

import com.alimentos.api_alimentos.model.Alimento;
import com.alimentos.api_alimentos.service.AlimentoService;

@RestController
@RequestMapping("api/v1/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @GetMapping
    public ResponseEntity <List<Alimento>> listarAlimentos(){
        List <Alimento> alimentos = alimentoService.mostrarAlimento();
        if(alimentos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alimentos);
    }

    @PostMapping
    public ResponseEntity<Alimento> guardarAlimento(@RequestBody Alimento alimento){
        Alimento nuevoAlimento = alimentoService.crearAlimento(alimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlimento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> buscarAlimento(@PathVariable Long id){
        try{
            Alimento alimento = alimentoService.buscarId(id);
            return ResponseEntity.ok(alimento);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alimento> modificarAlimento(@PathVariable Long id, @RequestBody Alimento alimento){
        try{
            Alimento ali = alimentoService.buscarId(id);
            ali.setId_alimento(id);
            ali.setNombre(alimento.getNombre());
            ali.setProteinaG(alimento.getProteinaG());
            ali.setCarbohidratoG(alimento.getCarbohidratoG());
            ali.setGrasaG(alimento.getGrasaG());
            ali.setCalorias(alimento.getCalorias());

            ali.setCategoria(alimento.getCategoria());
            
            alimentoService.crearAlimento(ali);
            return ResponseEntity.ok(alimento);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/calorias-max/{calorias}")
    public ResponseEntity<List<Alimento>> listarPorCaloriasMaximas(@PathVariable Double calorias) {
    List<Alimento> alimentos = alimentoService.buscarPorCaloriasMaximas(calorias);
    
    if (alimentos.isEmpty()) {
        return ResponseEntity.noContent().build(); 
    }  
    return ResponseEntity.ok(alimentos); 
    }

    @DeleteMapping("/{id}")
    public String eliminarAlimento(@PathVariable Long id){
        try{
            alimentoService.eliminarAlimento(id);
            return "El alimento ha sido eliminado";
        }catch (Exception e){
            return "El alimento no existe";
        }
    }

}
