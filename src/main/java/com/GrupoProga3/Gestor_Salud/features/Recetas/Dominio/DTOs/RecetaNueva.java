package com.GrupoProga3.Gestor_Salud.features.Recetas.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.features.DetallesRecetas.Dominio.DTOs.DetalleRecetaNuevo;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RecetaNueva(@NotNull
                          Long id_paciente,
                          @NotNull
                          Long id_profesional,
                          List<DetalleRecetaNuevo> detalles) {
}
