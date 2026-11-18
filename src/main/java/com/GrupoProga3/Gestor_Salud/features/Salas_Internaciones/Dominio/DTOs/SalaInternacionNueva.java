package com.GrupoProga3.Gestor_Salud.features.Salas_Internaciones.Dominio.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SalaInternacionNueva(
        @NotNull(message = "La capacidad máxima es obligatoria.")
        @Positive(message = "La capacidad máxima debe ser mayor a 0.")
        Integer capacidad_maxima) {
}
