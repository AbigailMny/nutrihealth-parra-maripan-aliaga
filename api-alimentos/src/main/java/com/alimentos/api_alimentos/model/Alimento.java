package com.alimentos.api_alimentos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "alimento")
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_alimento;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Double proteinaG;

    @Column(nullable = false)
    private Double grasaG;

    @Column(nullable = false) 
    private Double carbohidratoG;

    @Column(nullable = false)
    private Double calorias;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties({"alimentos"}) 
    private Categoria categoria;
    
}
