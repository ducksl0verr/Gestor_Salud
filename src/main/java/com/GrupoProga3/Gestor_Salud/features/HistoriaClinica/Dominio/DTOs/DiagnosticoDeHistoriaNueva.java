package com.GrupoProga3.Gestor_Salud.features.HistoriaClinica.Dominio.DTOs;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DiagnosticoDeHistoriaNueva(@NotBlank(message = "El diagnostico debe tener un nombre") String nombre,
                                         @NotBlank String descripcion,
                                         LocalDate fechaDiagnostico) {
}
