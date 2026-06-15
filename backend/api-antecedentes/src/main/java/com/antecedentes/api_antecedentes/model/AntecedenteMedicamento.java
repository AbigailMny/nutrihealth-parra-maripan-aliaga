package com.antecedentes.api_antecedentes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "antecedente_medicamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AntecedenteMedicamento {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private AntecedenteMedicamentoId id = new AntecedenteMedicamentoId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("antecedenteId")
    @JoinColumn(name = "antecedente_id")
    private AntecedentesClinicos antecedente;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("medicamentoId")
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;

    @Column(length = 100)
    private String dosis;
}
