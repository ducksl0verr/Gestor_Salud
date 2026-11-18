package com.GrupoProga3.Gestor_Salud.features.Recetas.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.features.DetallesRecetas.Dominio.DTOs.DetalleRecetaRespuesta;

import java.time.LocalDate;
import java.util.List;

public record RecetaRespuesta(Long id,
                              LocalDate fecha,
                              String descripcion,
                              Long id_paciente,
                              String nombre_paciente,
                              String apellido_paciente,
                              Long id_profesional,
                              String nombre_profesional,
                              String apellido_profesional,
                              List<DetalleRecetaRespuesta> detalles) {
}
