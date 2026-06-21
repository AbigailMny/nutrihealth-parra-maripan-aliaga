package com.metas.api_metas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "progresos_meta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgresoMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meta_id", nullable = false)
    private Meta meta;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "valor_alcanzado", nullable = false, precision = 8, scale = 2)
    private BigDecimal valorAlcanzado;
}
