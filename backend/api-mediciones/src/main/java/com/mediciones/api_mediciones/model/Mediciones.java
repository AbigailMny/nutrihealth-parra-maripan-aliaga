package com.mediciones.api_mediciones.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="mediciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mediciones 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="paciente_id", nullable=false)
    private Long pacienteId;

    @Column(name="fecha_medicion", nullable=false)
    private Date fechaMedicion;

    @Column(name="peso_kg", precision=5, scale=2)
    private BigDecimal pesoKg;

    @Column(name="talla_cm", precision=5, scale=2)
    private BigDecimal tallaCm;

    @Column(name="grasa_porcentaje", precision=5, scale=2)
    private BigDecimal grasaPorcentaje;

    @Column(name="musculo_porcentaje", precision=5, scale=2)
    private BigDecimal musculoPorcentaje;




}
