package com.recetas.api_recetas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "receta_ingredientes")
public class RecetaIngredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ingredientes;

    @Column(nullable = false)
    private Long id_alimento;

    @Column(nullable = false)
    private Double cantidadGramos;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    @JsonBackReference
    private Receta receta;
}
