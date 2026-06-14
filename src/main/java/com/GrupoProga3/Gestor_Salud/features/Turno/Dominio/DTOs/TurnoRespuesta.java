package com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.ENUMS.EstadoFacturacionDeTurno;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.ENUMS.EstadoTurno;

import java.time.LocalDateTime;

public record TurnoRespuesta(Long id,
                             LocalDateTime fechaHora,
                             EstadoFacturacionDeTurno estadoFacturacionDeTurno,
                             EstadoTurno estadoTurno,
                             Long id_paciente,
                             Long id_tratamiento,
                             Long id_consultorio,
                             Long id_profesional) {
}
