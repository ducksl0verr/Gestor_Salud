package com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs;

import java.sql.Time;
import java.util.Date;

public record TurnoRespuesta(Long id_turno,
                             Date fecha,
                             Time hora,
                             String estado,
                             Long id_paciente,
                             Long id_tratamiento,
                             Long id_sala,
                             Long id_profesional) {
}
