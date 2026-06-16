package com.hospital.service_auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.service_auth.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

	Optional<Rol> findByNombreRol(String nombreRol);
}
