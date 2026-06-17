package com.hospital.service_auth.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString(exclude = "usuarios")
@EqualsAndHashCode(exclude = "usuarios")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre_rol", nullable = false, unique = true)
	private String nombreRol;

	@Builder.Default
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Set<Usuario> usuarios = new HashSet<>();
}
