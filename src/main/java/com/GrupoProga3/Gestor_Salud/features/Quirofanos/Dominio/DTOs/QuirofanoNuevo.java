package com.GrupoProga3.Gestor_Salud.features.Quirofanos.Dominio.DTOs;

import jakarta.validation.constraints.NotBlank;

public record QuirofanoNuevo(@NotBlank
                             String nombre) {
}
