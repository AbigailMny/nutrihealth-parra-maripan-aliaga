package com.hospital.service_auth.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hospital.service_auth.model.Rol;
import com.hospital.service_auth.repository.RolRepository;

@Configuration
public class DataSeeder {

	@Bean
	public CommandLineRunner seedRoles(RolRepository rolRepository) {
		return args -> {
			List.of("PACIENTE", "NUTRICIONISTA", "ADMINISTRADOR", "ADMINISTRATIVO")
					.forEach(nombreRol -> {
						if (rolRepository.findByNombreRol(nombreRol).isEmpty()) {
							rolRepository.save(Rol.builder().nombreRol(nombreRol).build());
						}
					});
		};
	}
}
