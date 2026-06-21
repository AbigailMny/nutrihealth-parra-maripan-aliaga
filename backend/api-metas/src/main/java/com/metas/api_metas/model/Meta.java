package com.metas.api_metas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "metas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @Column(name = "nombre_meta", nullable = false, length = 100)
    private String nombreMeta;

    @Column(name = "valor_objetivo", nullable = false, precision = 8, scale = 2)
    private BigDecimal valorObjetivo;

    @Column(name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida;

    @OneToMany(mappedBy = "meta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgresoMeta> progresos;
}
