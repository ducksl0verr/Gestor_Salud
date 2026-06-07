package com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Turno.Dominio.ENUMS.EstadoTurno;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record TurnoActualizar(@DateTimeFormat LocalDate fecha,
                              LocalTime hora,
                              EstadoTurno estadoTurno) {
}
