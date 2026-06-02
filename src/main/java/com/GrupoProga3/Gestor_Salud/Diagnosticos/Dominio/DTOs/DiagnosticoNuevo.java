package com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.DTOs;

import jakarta.validation.constraints.NotBlank;

public record DiagnosticoNuevo(@NotBlank(message = "El diagnostico debe tener un nombre") String nombre, @NotBlank String descripcion) {
}
