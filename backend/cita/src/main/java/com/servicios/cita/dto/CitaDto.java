package com.servicios.cita.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaDto {
    private Long id;
    private Long idPaciente;
    private Long idNutricionista;
    private LocalDateTime fechaHoraInicio;
    private String motivo;
    /**
     * Estado de la cita: P=Pendiente, C=Confirmada, X=Cancelada, R=Realizada
     */
    private String estado;
}
