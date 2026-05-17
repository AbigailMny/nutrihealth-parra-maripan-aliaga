package com.recetas.api_recetas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recetas.api_recetas.client.ExternalServiceClient;
import com.recetas.api_recetas.dto.AlimentosDto;
import com.recetas.api_recetas.dto.RecetaIngredienteDto;
import com.recetas.api_recetas.model.RecetaIngredientes;
import com.recetas.api_recetas.repository.IngredientesRepository;
import com.recetas.api_recetas.repository.RecetaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class IngredientesService {

    @Autowired
    private IngredientesRepository ingredientesRepository;

    @Autowired RecetaRepository recetaRepository;

    private final ExternalServiceClient client;

    public IngredientesService(ExternalServiceClient client) {
        this.client = client;
    }

    public List<RecetaIngredientes> mostrarIngredientes(){
        return ingredientesRepository.findAll();
    }

    public RecetaIngredientes buscarId(Long id){
        return ingredientesRepository.findById(id).get();
    }

    public RecetaIngredientes crearIngredientes( RecetaIngredientes ing){
        AlimentosDto alimento = client.obtenerAlimento(ing.getId_alimento());
        if(alimento == null){
            throw new RuntimeException("Alimento no existe");
        }
        return ingredientesRepository.save(ing);

    }

    public RecetaIngredienteDto buscarIdConAlimento(Long id) {
        RecetaIngredientes ing = buscarId(id);
        AlimentosDto alimentoExterno = client.obtenerAlimento(ing.getId_alimento());
        
        RecetaIngredienteDto dto = new RecetaIngredienteDto();
        dto.setId_ingredientes(ing.getId_ingredientes());
        dto.setCantidadGramos(ing.getCantidadGramos());
        dto.setAlimento(alimentoExterno);
        
        return dto;
    }

    public void eliminarIngrediente(Long id){
        ingredientesRepository.deleteById(id);
    }
}
