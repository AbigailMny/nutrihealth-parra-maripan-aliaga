package com.hospital.service_auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.service_auth.dto.AuthRequest;
import com.hospital.service_auth.dto.AuthResponse;
import com.hospital.service_auth.dto.RegisterRequest;
import com.hospital.service_auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Autenticacion", description = "Registro y login con JWT")
public class AutenticacionController {

	private final AuthService authService;

	@PostMapping("/register")
	@Operation(summary = "Registrar usuario", description = "Crea un usuario y devuelve un JWT inicial")
	public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
	}

	@PostMapping("/login")
	@Operation(summary = "Iniciar sesion", description = "Valida credenciales y devuelve un JWT")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
}
