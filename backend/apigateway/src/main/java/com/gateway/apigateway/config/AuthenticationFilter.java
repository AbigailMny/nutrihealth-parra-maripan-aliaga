package com.gateway.apigateway.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationFilter implements GlobalFilter {

	private final JwtService jwtService;

	@Autowired
	public AuthenticationFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	public Mono<Void> filter(org.springframework.web.server.ServerWebExchange exchange,
			org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
		String path = exchange.getRequest().getPath().value();
		if (isPublicPath(path)) {
			return chain.filter(exchange);
		}

		String authorization = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}

		String token = authorization.substring(7);
		if (!jwtService.validarToken(token)) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}

		return chain.filter(exchange);
	}

	private boolean isPublicPath(String path) {
		List<String> publicPrefixes = List.of(
				"/auth/",
				"/swagger-ui",
				"/v3/api-docs",
				"/api/v1/nutricionistas/v3/api-docs",
				"/api/v1/pacientes/v3/api-docs",
				"/api/v1/recetas/v3/api-docs",
				"/api/v1/alimentos/v3/api-docs",
				"/api/v1/minutas/v3/api-docs",
				"/api/v1/antecedentes/v3/api-docs",
				"/api/v1/rutinas/v3/api-docs");
		return publicPrefixes.stream().anyMatch(path::startsWith);
	}
}
