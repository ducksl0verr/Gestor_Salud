package com.GrupoProga3.Gestor_Salud.features.Quirofanos.Dominio.DTOs;

import jakarta.validation.constraints.NotBlank;

public record QuirofanoActualizar(@NotBlank String nombre,
                                  Boolean disponible) {
}
