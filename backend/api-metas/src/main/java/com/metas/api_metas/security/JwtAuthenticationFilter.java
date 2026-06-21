package com.metas.api_metas.security;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final String INTERNAL_HEADER = "X-Internal-Request";

	private final JwtService jwtService;
	private final String internalSecret;

	public JwtAuthenticationFilter(JwtService jwtService, String internalSecret) {
		this.jwtService = jwtService;
		this.internalSecret = internalSecret;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String internalHeader = request.getHeader(INTERNAL_HEADER);
		if (internalSecret.equals(internalHeader)) {
			setAuthentication("internal-service", List.of("INTERNAL"));
			filterChain.doFilter(request, response);
			return;
		}

		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authorization != null && authorization.startsWith("Bearer ")) {
			String token = authorization.substring(7);
			if (!jwtService.validarToken(token)) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token invalido");
				return;
			}
			setAuthentication(jwtService.extraerNombreUsuario(token), jwtService.extraerRoles(token).stream().toList());
		}

		filterChain.doFilter(request, response);
	}

	private void setAuthentication(String username, List<String> roles) {
		var authorities = roles.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
				.toList();
		UsernamePasswordAuthenticationToken authentication =
				new UsernamePasswordAuthenticationToken(username, null, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
