package com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.DTOs;

import java.time.LocalDate;

public record PrescripcionTratamientoRespuesta(Long id,
                                               Long id_tratamiento,
                                               String nombre_tratamiento,
                                               Boolean activo,
                                               LocalDate fecha_inicio,
                                               LocalDate fecha_final) {
}
