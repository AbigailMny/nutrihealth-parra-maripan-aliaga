package com.hospital.service_auth.dto;

import java.time.Instant;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	private String mensaje;
	private String token;
	private String tipoToken;
	private String nombreUsuario;
	private Set<String> roles;
	private Instant expiraEn;
}
