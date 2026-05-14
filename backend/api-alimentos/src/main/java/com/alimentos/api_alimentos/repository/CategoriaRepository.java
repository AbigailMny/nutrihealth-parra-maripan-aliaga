package com.alimentos.api_alimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alimentos.api_alimentos.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
