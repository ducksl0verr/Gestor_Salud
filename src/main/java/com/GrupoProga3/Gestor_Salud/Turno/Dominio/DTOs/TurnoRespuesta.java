package com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Turno.Dominio.ENUMS.EstadoFacturacionDeTurno;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.ENUMS.EstadoTurno;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public record TurnoRespuesta(Long id,
                             LocalDateTime fechaHora,
                             EstadoFacturacionDeTurno estadoFacturacionDeTurno,
                             EstadoTurno estadoTurno,
                             Long id_paciente,
                             Long id_tratamiento,
                             Long id_consultorio,
                             Long id_profesional) {
}
