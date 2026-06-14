package com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record TurnoFacturable(Long id_turno,
                              LocalDateTime fechaHora,
                              String tratamiento) {
}
