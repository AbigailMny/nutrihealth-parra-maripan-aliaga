package com.antecedentes.api_antecedentes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Transient;
import com.antecedentes.api_antecedentes.dto.PacienteDTO;

@Entity
@Table(name = "antecedentes_clinicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AntecedentesClinicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "paciente_id", nullable = false, unique = true)
    private Long pacienteId;

    @Column(name = "tipo_sangre", length = 5)
    private String tipoSangre;

    @Column(name = "observaciones_generales", columnDefinition = "TEXT")
    private String observacionesGenerales;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "antecedente_enfermedad",
            joinColumns = @JoinColumn(name = "antecedente_id"),
            inverseJoinColumns = @JoinColumn(name = "enfermedad_id")
    )
    private Set<Enfermedad> enfermedades = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "antecedente_alergia",
            joinColumns = @JoinColumn(name = "antecedente_id"),
            inverseJoinColumns = @JoinColumn(name = "alergia_id")
    )
    private Set<Alergia> alergias = new HashSet<>();
    
        @OneToMany(mappedBy = "antecedente", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<AntecedenteMedicamento> medicamentos = new HashSet<>();

        @Transient
        private PacienteDTO paciente;

}
