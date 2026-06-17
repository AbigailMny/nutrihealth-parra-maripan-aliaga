package com.hospital.service_auth.service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.service_auth.dto.AuthRequest;
import com.hospital.service_auth.dto.AuthResponse;
import com.hospital.service_auth.dto.RegisterRequest;
import com.hospital.service_auth.model.Rol;
import com.hospital.service_auth.model.Usuario;
import com.hospital.service_auth.repository.RolRepository;
import com.hospital.service_auth.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UsuarioRepository usuarioRepository;
	private final RolRepository rolRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	@Transactional
	public AuthResponse register(RegisterRequest request) {
		if (usuarioRepository.existsByNombreUsuarioOrCorreo(request.getNombreUsuario(), request.getCorreo())) {
			throw new IllegalArgumentException("El usuario o correo ya existe");
		}

		Set<String> nombresRoles = request.getRoles() == null || request.getRoles().isEmpty()
				? Set.of("PACIENTE")
				: new HashSet<>(request.getRoles());

		Set<Rol> roles = nombresRoles.stream()
				.map(this::obtenerOCrearRol)
				.collect(java.util.stream.Collectors.toSet());

		Usuario usuario = Usuario.builder()
				.nombreUsuario(request.getNombreUsuario())
				.contrasena(passwordEncoder.encode(request.getContrasena()))
				.correo(request.getCorreo())
				.roles(roles)
				.build();

		Usuario guardado = usuarioRepository.save(usuario);
		String token = jwtService.generarToken(guardado);

		return AuthResponse.builder()
				.mensaje("Usuario registrado correctamente")
				.token(token)
				.tipoToken("Bearer")
				.nombreUsuario(guardado.getNombreUsuario())
				.roles(jwtService.extraerRoles(token))
				.expiraEn(jwtService.extraerExpiracion(token))
				.build();
	}

	public AuthResponse login(AuthRequest request) {
		Usuario usuario = usuarioRepository.findByNombreUsuario(request.getNombreUsuario())
				.orElseThrow(() -> new IllegalArgumentException("Credenciales invalidas"));

		if (!passwordEncoder.matches(request.getContrasena(), usuario.getContrasena())) {
			throw new IllegalArgumentException("Credenciales invalidas");
		}

		String token = jwtService.generarToken(usuario);

		return AuthResponse.builder()
				.mensaje("Inicio de sesion exitoso")
				.token(token)
				.tipoToken("Bearer")
				.nombreUsuario(usuario.getNombreUsuario())
				.roles(jwtService.extraerRoles(token))
				.expiraEn(jwtService.extraerExpiracion(token))
				.build();
	}

	private Rol obtenerOCrearRol(String nombreRol) {
		return rolRepository.findByNombreRol(nombreRol)
				.orElseGet(() -> rolRepository.save(Rol.builder().nombreRol(nombreRol).build()));
	}
}
