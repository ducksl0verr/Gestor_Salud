package com.GrupoProga3.Gestor_Salud.auth.DTOs;

import jakarta.validation.constraints.NotBlank;

public record AuthPedido(@NotBlank String username,
                         @NotBlank String password) {
}
