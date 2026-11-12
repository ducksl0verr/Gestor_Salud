package com.GrupoProga3.Gestor_Salud.auth.TokensRecuperacion.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record OlvideContrasenaPedido(@NotBlank @Email String email) {}
