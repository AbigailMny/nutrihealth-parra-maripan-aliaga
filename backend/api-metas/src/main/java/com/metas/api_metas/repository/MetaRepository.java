package com.metas.api_metas.repository;

import com.metas.api_metas.model.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Long> {
    List<Meta> findByPacienteId(Long pacienteId);
}
