package com.GrupoProga3.Gestor_Salud.features.Medicamentos.Dominio.DTOs;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record MedicamentoNuevo(@NotBlank
                               String nombre,
                               @NotBlank
                               String principioActivo,
                               @NotBlank
                               String laboratorio,
                               @NotBlank
                               String descripcion,
                               @NotNull
                               @Positive
                               Integer stock,
                               @NotNull
                               @Positive
                               Double precio,
                               @NotNull
                               @Future(message = "La fecha de vencimiento debe ser posterior al día de hoy.")
                               LocalDate fechaVencimiento) {
}
