package com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public record TurnoRespuesta(Long id_turno,
                             LocalDate fecha,
                             LocalTime hora,
                             String estado,
                             Long id_paciente,
                             Long id_tratamiento,
                             Long id_consultorio,
                             Long id_profesional) {
}
