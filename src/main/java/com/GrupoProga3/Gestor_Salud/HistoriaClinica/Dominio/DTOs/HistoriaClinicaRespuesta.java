package com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs;

import java.time.LocalDate;

public record HistoriaClinicaRespuesta(Long id,
                                       LocalDate fecha,
                                       String observaciones,
                                       String evolucion,
                                       Long id_paciente,
                                       Long id_profesional) {
}
