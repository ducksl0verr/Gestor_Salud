package com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

public record TurnoFacturable(Long id_turno,
                              LocalDate fecha,
                              LocalTime hora,
                              String tratamiento) {
}
