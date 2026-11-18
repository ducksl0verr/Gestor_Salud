package com.GrupoProga3.Gestor_Salud.features.Recetas.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.features.DetallesRecetas.Dominio.DTOs.DetalleRecetaNuevo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RecetaNueva(@NotNull
                          Long id_paciente,
                          @NotNull
                          Long id_profesional,
                          @Size(max = 200, message = "La descripción no puede superar los 200 caracteres.")
                          String descripcion,
                          List<DetalleRecetaNuevo> detalles) {
}
