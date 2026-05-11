package com.recetas.api_recetas.dto;
import lombok.Data;

@Data
public class RecetasDto {
    private Long id_receta;
    private String nombre_plato;
    private String preparacion;
}
