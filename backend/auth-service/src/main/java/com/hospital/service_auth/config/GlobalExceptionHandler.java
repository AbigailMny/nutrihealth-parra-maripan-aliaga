package com.hospital.service_auth.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.UNAUTHORIZED.value());
		body.put("error", "Unauthorized");
		body.put("message", ex.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
		ex.printStackTrace(); // Imprime en consola
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		body.put("error", "Internal Server Error");
		body.put("message", ex.getMessage());
		body.put("type", ex.getClass().getName());
		
		// Capturar los primeros elementos del stack trace para ver el origen exacto
		java.io.StringWriter sw = new java.io.StringWriter();
		java.io.PrintWriter pw = new java.io.PrintWriter(sw);
		ex.printStackTrace(pw);
		body.put("trace", sw.toString().substring(0, Math.min(sw.toString().length(), 1000)));
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}
}
