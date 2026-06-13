package com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Turno.Dominio.ENUMS.EstadoTurno;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record TurnoActualizar(@Future LocalDateTime fechaHora,
                              @NotNull EstadoTurno estadoTurno) {
}
