package com.GrupoProga3.Gestor_Salud.Notificaciones;

import jakarta.validation.constraints.NotBlank;

public record MensajeDTO(@NotBlank
                         String asunto,
                         @NotBlank
                         String mensaje) {
}
