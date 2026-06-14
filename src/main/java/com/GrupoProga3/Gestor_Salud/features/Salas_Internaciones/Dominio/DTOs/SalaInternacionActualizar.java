package com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SalaInternacionActualizar(@NotNull
                                        Integer capacidad_maxima) {
}
