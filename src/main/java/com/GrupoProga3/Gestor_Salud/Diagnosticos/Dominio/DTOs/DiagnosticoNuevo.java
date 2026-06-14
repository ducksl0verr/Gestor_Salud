package com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DiagnosticoNuevo(@NotBlank(message = "El diagnostico debe tener un nombre") String nombre,
                               @NotBlank String descripcion,
                               @NotNull
                               LocalDate fechaDiagnostico,
                               @NotNull
                               Long idHistoriaClinica) {
}
