package com.GrupoProga3.Gestor_Salud.features.Especialidades.Dominio.DTOs;

import jakarta.validation.constraints.NotBlank;

public record EspecialidadNueva(@NotBlank(message = "El profesional debe tener una especialidad") String nombre) {
}
