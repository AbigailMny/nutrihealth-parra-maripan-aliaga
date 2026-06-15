package com.antecedentes.api_antecedentes.repository;

import com.antecedentes.api_antecedentes.model.Enfermedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfermedadRepository extends JpaRepository<Enfermedad, Long> {
}
