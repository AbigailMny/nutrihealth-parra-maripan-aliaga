package com.servicios.cita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servicios.cita.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

}
