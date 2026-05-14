package com.alimentos.api_alimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alimentos.api_alimentos.model.Alimento;

public interface AlimentoRepository extends JpaRepository<Alimento, Long>{

}
