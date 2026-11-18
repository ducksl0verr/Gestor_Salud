package com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.ENUMS.EstadoTurno;
import jakarta.validation.constraints.NotNull;

public record CambioEstadoTurno(@NotNull EstadoTurno estado) {
}
