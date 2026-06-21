package com.mediciones.api_mediciones.security;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${security.jwt.secret:defaultSecretdefaultSecretdefaultSecret}")
	private String jwtSecret;

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

