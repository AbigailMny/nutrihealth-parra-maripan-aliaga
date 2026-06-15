package com.minutas.api_minutas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NutricionistaDto {
    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
}
