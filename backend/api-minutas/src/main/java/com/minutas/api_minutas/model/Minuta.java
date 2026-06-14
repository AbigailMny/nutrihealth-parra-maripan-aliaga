package com.minutas.api_minutas.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "minutas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Minuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long paciente_id;

    @Column(nullable = false)
    private Long nutricionista_id;

    @Column(nullable = false)
    private LocalDate fecha_inicio;

    @Column(nullable = true)
    private LocalDate fecha_fin;

    @Column(nullable = true)
    private String url_archivo;

    @Column(nullable = true)
    private String estado;
}

