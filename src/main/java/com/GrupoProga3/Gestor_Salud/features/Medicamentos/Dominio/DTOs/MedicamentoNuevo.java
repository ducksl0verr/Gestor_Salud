package com.GrupoProga3.Gestor_Salud.features.Medicamentos.Dominio.DTOs;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
                               Integer stock,
                               @NotNull
                               Double precio,
                               @NotNull
                               @Future
                               LocalDate fechaVencimiento) {
}
