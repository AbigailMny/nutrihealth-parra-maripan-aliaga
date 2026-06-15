package com.antecedentes.api_antecedentes.repository;

import com.antecedentes.api_antecedentes.model.AntecedenteMedicamento;
import com.antecedentes.api_antecedentes.model.AntecedenteMedicamentoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntecedenteMedicamentoRepository extends JpaRepository<AntecedenteMedicamento, AntecedenteMedicamentoId> {
}
