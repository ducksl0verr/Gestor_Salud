package com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.DTOs;

import jakarta.validation.constraints.NotBlank;

public record ConsultorioActualizar(@NotBlank String nombre) {
}
