package com.antecedentes.api_antecedentes.repository;

import com.antecedentes.api_antecedentes.model.AntecedentesClinicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AntecedentesClinicosRepository extends JpaRepository<AntecedentesClinicos, Long> {
    Optional<AntecedentesClinicos> findByPacienteId(Long pacienteId);
}
