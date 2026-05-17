package com.servicio.paciente.repository;

import com.servicio.paciente.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByRun(String run);
    Optional<Paciente> findByCorreo(String correo);
    List<Paciente> findByNombresContainingIgnoreCase(String nombres);
    List<Paciente> findByApellidosContainingIgnoreCase(String apellidos);
    List<Paciente> findByNombresContainingIgnoreCaseAndApellidosContainingIgnoreCase(String nombres, String apellidos);
}
