package com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.Dominio;

import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;

import java.time.LocalDate;

public record PrescripcionTratamientoRespuesta(Long id,
                                               Long id_tratamiento,
                                               String nombre_tratamiento,
                                               Boolean activo,
                                               LocalDate fecha_inicio,
                                               LocalDate fecha_final) {
}
