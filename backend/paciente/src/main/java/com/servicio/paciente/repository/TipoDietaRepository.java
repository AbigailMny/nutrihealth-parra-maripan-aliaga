package com.servicio.paciente.repository;

import com.servicio.paciente.model.TipoDieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoDietaRepository extends JpaRepository<TipoDieta, Long> {
    Optional<TipoDieta> findByNombre(String nombre);
}
