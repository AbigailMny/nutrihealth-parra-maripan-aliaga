package com.recetas.api_recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.recetas.api_recetas.model.RecetaIngredientes;

@Repository
public interface IngredientesRepository extends JpaRepository<RecetaIngredientes, Long>{

}
