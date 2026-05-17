package com.recetas.api_recetas.dto;

import lombok.Data;

@Data   
public class RecetaIngredienteDto {
    private Long id_ingredientes;
    private Double cantidadGramos;
    
    private AlimentosDto alimento; 
}
