package com.GrupoProga3.Gestor_Salud.features.Recetas.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.features.DetallesRecetas.Dominio.DTOs.DetalleRecetaRespuesta;

import java.time.LocalDate;
import java.util.List;

public record RecetaRespuesta(Long id,
                              LocalDate fecha,
                              Long id_paciente,
                              Long id_profesional,
                              List<DetalleRecetaRespuesta> detalles) {
}
