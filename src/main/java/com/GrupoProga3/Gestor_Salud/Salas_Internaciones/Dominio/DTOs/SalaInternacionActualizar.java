package com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SalaInternacionActualizar(@NotNull
                                        @Size(min=1, max = 5)
                                        Integer capacidad_maxima) {
}
