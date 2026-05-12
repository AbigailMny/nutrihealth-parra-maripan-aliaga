package com.recetas.api_recetas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recetas.api_recetas.model.Receta;
import com.recetas.api_recetas.repository.RecetaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    public List<Receta> mostrarRecetas(){
        return recetaRepository.findAll();
    }

    public Receta buscarId(Long id){
        return recetaRepository.findById(id).get();
    }

    public Receta crearReceta(Receta receta){
        return recetaRepository.save(receta);
    }

    public void eliminarReceta(Long id){
        recetaRepository.deleteById(id);
    }
}
