package com.antecedentes.api_antecedentes.repository;

import com.antecedentes.api_antecedentes.model.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia, Long> {
}
