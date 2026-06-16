package com.minutas.api_minutas.config;

import com.minutas.api_minutas.security.JwtAuthenticationFilter;
import com.minutas.api_minutas.security.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter(
			JwtService jwtService,
			@Value("${security.internal.secret}") String internalSecret) {
		return new JwtAuthenticationFilter(jwtService, internalSecret);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/api/v1/minutas/v3/api-docs", "/error").permitAll()
						.requestMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMINISTRADOR", "ADMIN", "NUTRICIONISTA", "PACIENTE", "INTERNAL")
						.requestMatchers(HttpMethod.POST, "/**").hasAnyRole("ADMINISTRADOR", "ADMIN", "NUTRICIONISTA", "INTERNAL")
						.requestMatchers(HttpMethod.PUT, "/**").hasAnyRole("ADMINISTRADOR", "ADMIN", "NUTRICIONISTA", "INTERNAL")
						.requestMatchers(HttpMethod.DELETE, "/**").hasAnyRole("ADMINISTRADOR", "ADMIN", "INTERNAL")
						.anyRequest().authenticated())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}
