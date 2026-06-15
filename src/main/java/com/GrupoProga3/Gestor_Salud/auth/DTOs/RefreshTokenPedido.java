package com.GrupoProga3.Gestor_Salud.auth.DTOs;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenPedido(@NotBlank String refreshToken) {
}
