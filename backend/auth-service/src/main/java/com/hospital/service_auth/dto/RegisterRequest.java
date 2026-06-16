package com.hospital.service_auth.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

	@NotBlank
	private String nombreUsuario;

	@NotBlank
	private String contrasena;

	@NotBlank
	@Email
	private String correo;

	private Set<String> roles;
}
