package com.rutinas.api_rutinas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rutinas.api_rutinas.model.EjercicioRutina;

@Repository
public interface EjercicioRutinaRepository extends JpaRepository<EjercicioRutina, Long> {
    List<EjercicioRutina> findByRutinaId(Long rutinaId);
    void deleteByRutinaId(Long rutinaId);
}
