package com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs;

import jakarta.validation.constraints.NotBlank;

public record HistoriaClinicaActualizar(@NotBlank String observaciones,
                                        @NotBlank String evolucion) {
}
