package com.servicio.nutricionista.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Modelo DTO que representa los datos de una Nutricionista en NutriHealth")
public class NutricionistaDTO {
    @Schema(description = "Identificador único autoincremental", example = "1", readOnly = true)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Schema(description = "Nombres completos de la nutricionista", example = "María Fernanda", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombres;

    @NotBlank(message = "Los apellidos no pueden estar vacíos")
    @Size(max = 100, message = "Los apellidos no pueden superar los 100 caracteres")
    @Schema(description = "Apellidos de la nutricionista", example = "Pérez Soto", requiredMode = Schema.RequiredMode.REQUIRED)
    private String apellidos;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El formato del correo electrónico es inválido")
    @Size(max = 150, message = "El correo no puede superar los 150 caracteres")
    @Schema(description = "Dirección de correo electrónico de contacto", example = "nutricionista@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String correo;

    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    @Schema(description = "Teléfono móvil o fijo de contacto", example = "+56912345678")
    private String telefono;
}
