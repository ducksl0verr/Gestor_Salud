package com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.ENUMS.EstadoFacturacionDeTurno;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.ENUMS.EstadoTurno;

import java.time.LocalDateTime;

public record TurnoRespuesta(Long id,
                             LocalDateTime fechaHora,
                             EstadoFacturacionDeTurno estadoFacturacionDeTurno,
                             EstadoTurno estadoTurno,
                             String nombrePaciente,
                             String nombreTratamiento,
                             String nombreConsultorio,
                             String nombreProfesional) {
}
