package com.alimentos.api_alimentos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alimentos.api_alimentos.model.Categoria;
import com.alimentos.api_alimentos.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> mostrarCategorias(){
        return categoriaRepository.findAll();
    }

    public Categoria buscarId(Long id){
        return categoriaRepository.findById(id).get();
    }

    public Categoria crearCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void eliminarCategoria(Long id){
        categoriaRepository.deleteById(id);
    }

}
