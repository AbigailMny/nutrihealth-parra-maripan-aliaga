package com.mediciones.api_mediciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mediciones.api_mediciones.model.Mediciones;

@Repository
public interface MedicionRepository extends JpaRepository<Mediciones, Long> {
    List<Mediciones> findByPacienteId(Long pacienteId);

}
