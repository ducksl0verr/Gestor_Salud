package com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SalaInternacionActualizar(@NotNull
                                        @Min(value = 1, message = "La capacidad mínima es 1")
                                        @Max(value = 5, message = "La capacidad máxima es 5")
                                        Integer capacidad_maxima) {
}
