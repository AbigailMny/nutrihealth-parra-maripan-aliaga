package com.rutinas.api_rutinas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rutinas.api_rutinas.model.Rutina;

@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Long> {
    List<Rutina> findByPacienteId(Long pacienteId);
}
