package com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public record TurnoActualizar(@DateTimeFormat LocalDate fecha,
                              LocalTime hora,
                              @NotBlank String estado) {
}
