package com.recetas.api_recetas.dto;

import lombok.Data;

@Data
public class AlimentosDto {
    private Long id_alimento;
    private String nombre;
    private Double proteinaG;
    private Double grasaG;
    private Double carbohidratoG;
    private Double calorias;

}
