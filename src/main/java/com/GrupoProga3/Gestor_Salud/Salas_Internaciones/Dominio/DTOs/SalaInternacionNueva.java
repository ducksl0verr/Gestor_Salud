package com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs;

import jakarta.validation.constraints.NotNull;

public record SalaInternacionNueva(@NotNull Integer capacidad_maxima) {
}
