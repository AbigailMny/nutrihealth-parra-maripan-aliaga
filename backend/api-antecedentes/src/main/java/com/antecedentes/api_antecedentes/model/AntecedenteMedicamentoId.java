package com.antecedentes.api_antecedentes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AntecedenteMedicamentoId implements Serializable {

    @Column(name = "antecedente_id")
    private Long antecedenteId;

    @Column(name = "medicamento_id")
    private Long medicamentoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AntecedenteMedicamentoId that = (AntecedenteMedicamentoId) o;
        return Objects.equals(antecedenteId, that.antecedenteId) &&
                Objects.equals(medicamentoId, that.medicamentoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(antecedenteId, medicamentoId);
    }
}
