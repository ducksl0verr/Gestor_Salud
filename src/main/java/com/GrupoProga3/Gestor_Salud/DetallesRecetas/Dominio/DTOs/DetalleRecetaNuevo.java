package com.GrupoProga3.Gestor_Salud.DetallesRecetas.Dominio.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DetalleRecetaNuevo(@NotBlank
                                 String dosis,
                                 @NotBlank
                                 String frecuencia,
                                 @NotNull
                                 Integer cantidad,
                                 @NotNull
                                 Long id_medicamento) {
}
