package com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.DTOs;

import jakarta.validation.constraints.NotBlank;

public record ConsultorioActualizar(@NotBlank String nombre) {
}
