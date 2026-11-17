package com.GrupoProga3.Gestor_Salud.features.HistoriaClinica.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.DTOs.DiagnosticoRespuesta;

import java.time.LocalDate;
import java.util.List;

public record HistoriaClinicaRespuesta(Long id,
                                       LocalDate fecha,
                                       String observaciones,
                                       String evolucion,
                                       Long id_paciente,
                                       String nombre_paciente,
                                       String apellido_paciente,
                                       Long id_profesional,
                                       String nombre_profesional,
                                       String apellido_profesional,
                                       List <DiagnosticoRespuesta> diagnosticos) {
}
