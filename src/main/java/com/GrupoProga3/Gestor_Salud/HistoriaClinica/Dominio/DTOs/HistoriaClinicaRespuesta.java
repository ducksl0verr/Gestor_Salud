package com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.DTOs.DiagnosticoRespuesta;

import java.time.LocalDate;
import java.util.List;

public record HistoriaClinicaRespuesta(Long id,
                                       LocalDate fecha,
                                       String observaciones,
                                       String evolucion,
                                       Long id_paciente,
                                       Long id_profesional,
                                       List <DiagnosticoRespuesta> diagnosticos) {
}
