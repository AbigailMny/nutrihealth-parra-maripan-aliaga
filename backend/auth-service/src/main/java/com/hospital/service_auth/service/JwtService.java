package com.hospital.service_auth.service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hospital.service_auth.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${security.jwt.secret}")
	private String jwtSecret;

	@Value("${security.jwt.expiration-ms}")
	private long expirationMs;

	public String generarToken(Usuario usuario) {
		Instant issuedAt = Instant.now();
		Instant expiresAt = issuedAt.plusMillis(expirationMs);
		Set<String> roles = usuario.getRoles().stream()
				.map(rol -> rol.getNombreRol())
				.collect(Collectors.toSet());

		return Jwts.builder()
				.setSubject(usuario.getNombreUsuario())
				.claim("correo", usuario.getCorreo())
				.claim("roles", roles)
				.setIssuedAt(Date.from(issuedAt))
				.setExpiration(Date.from(expiresAt))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public boolean validarToken(String token) {
		try {
			parseClaims(token);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public String extraerNombreUsuario(String token) {
		return parseClaims(token).getSubject();
	}

	public Set<String> extraerRoles(String token) {
		Object rolesClaim = parseClaims(token).get("roles");
		if (rolesClaim instanceof List<?> roles) {
			return roles.stream().map(String::valueOf).collect(Collectors.toSet());
		}
		return Set.of();
	}

	public Instant extraerExpiracion(String token) {
		Date expiration = parseClaims(token).getExpiration();
		return expiration == null ? null : expiration.toInstant();
	}

	private Claims parseClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}
}
