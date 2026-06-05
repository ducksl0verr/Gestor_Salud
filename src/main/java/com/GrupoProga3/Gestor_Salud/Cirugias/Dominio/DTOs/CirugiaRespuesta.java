package com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.Enums.EstadoCirugia;

import java.time.LocalDate;

public record CirugiaRespuesta(Long id,
                               LocalDate fecha,
                               EstadoCirugia estado,
                               Long idPaciente,
                               String nombrePaciente,
                               Long idCirujano,
                               String nombreCirujano,
                               Long idQuirofano,
                               String nombreQuirofano) {
}
