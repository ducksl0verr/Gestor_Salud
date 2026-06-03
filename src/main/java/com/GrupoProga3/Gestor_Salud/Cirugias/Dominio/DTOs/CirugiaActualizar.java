package com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.DTOs;

import java.time.LocalDate;

public record CirugiaActualizar(LocalDate fecha,
                                Long idCirujano,
                                Long idQuirofano) {
}
