package com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.ENUMS.EstadoTurno;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TurnoActualizar(@Future LocalDateTime fechaHora,
                              @NotNull EstadoTurno estadoTurno) {
}
