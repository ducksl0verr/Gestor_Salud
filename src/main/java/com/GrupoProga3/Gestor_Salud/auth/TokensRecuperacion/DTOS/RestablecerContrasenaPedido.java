package com.GrupoProga3.Gestor_Salud.auth.TokensRecuperacion.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RestablecerContrasenaPedido(@NotBlank String token,
                                          @NotBlank @Size(min=8, max=64, message = "La contraseña debe tener entre 8 y 64 caracteres.")
                                          String nuevaContrasena) {
}
