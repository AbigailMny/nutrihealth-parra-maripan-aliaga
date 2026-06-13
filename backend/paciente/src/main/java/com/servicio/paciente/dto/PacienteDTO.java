package com.servicio.paciente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Modelo DTO que representa los datos de un Paciente en NutriHealth")
public class PacienteDTO {

    @Schema(description = "Identificador único autoincremental", example = "1", readOnly = true)
    private Long id;

    @NotBlank(message = "El RUN o RUT es obligatorio")
    @Size(min = 9, max = 12, message = "El RUN debe tener entre 9 y 12 caracteres")
    @Schema(description = "RUN o RUT del paciente con guión", example = "12345678-9", requiredMode = Schema.RequiredMode.REQUIRED)
    private String run;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombres completos del paciente", example = "Federico", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombres;

    @NotBlank(message = "Los apellidos no pueden estar vacíos")
    @Schema(description = "Apellidos del paciente", example = "Pin Pérez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String apellidos;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El formato del correo electrónico es inválido")
    @Schema(description = "Dirección de correo electrónico de contacto", example = "federico@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String correo;

    @Schema(description = "Teléfono móvil o fijo de contacto", example = "+56912345678")
    private String telefono;

    @NotNull(message = "Debe asignar un identificador de tipo de dieta")
    @Schema(description = "ID del tipo de dieta asociado al paciente", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long tipoDietaId;

    @Schema(description = "Detalles extendidos del tipo de dieta asignado")
    private TipoDietaDTO tipoDieta;

    @Schema(description = "Fecha de alta o registro del paciente en el sistema", example = "2026-04-24")
    private LocalDate fechaRegistro;
}