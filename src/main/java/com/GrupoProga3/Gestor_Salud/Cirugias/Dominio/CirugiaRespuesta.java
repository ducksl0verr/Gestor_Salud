package com.GrupoProga3.Gestor_Salud.Cirugias.Dominio;

import java.time.LocalDate;

public record CirugiaRespuesta(Long id,
                               LocalDate fecha,
                               Long idPaciente,
                               String nombrePaciente,
                               Long idCirujano,
                               String nombreCirujano,
                               Long idQuirofano,
                               String nombreQuirofano) {
}
