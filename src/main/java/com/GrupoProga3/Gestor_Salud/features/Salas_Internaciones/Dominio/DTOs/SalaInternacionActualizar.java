package com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SalaInternacionActualizar(@NotNull
                                        @Min(value = 1, message = "La capacidad máxima debe ser al menos 1.")
                                        @Max(value = 20, message = "La capacidad máxima no puede superar 20.")
                                        Integer capacidad_maxima) {
}
