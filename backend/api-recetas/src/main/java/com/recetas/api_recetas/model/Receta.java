package com.recetas.api_recetas.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "receta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_receta;

    @Column(nullable = false)
    private Long id_paciente;

    @Column(nullable = false)
    private String nombre_plato;

    @Column(nullable = false)
    private String preparacion;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = true)
    private String anotaciones;

    @OneToMany(mappedBy = "receta")
    @JsonManagedReference
    private List<RecetaIngredientes> ingredientes;
}
