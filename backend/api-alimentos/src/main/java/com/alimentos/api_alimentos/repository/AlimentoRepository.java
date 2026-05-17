package com.alimentos.api_alimentos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alimentos.api_alimentos.model.Alimento;

public interface AlimentoRepository extends JpaRepository<Alimento, Long>{
    List<Alimento> findByCaloriasLessThanEqual(Double calorias);
}
