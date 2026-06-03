package com.GrupoProga3.Gestor_Salud.Salas_Internaciones.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;

import java.util.List;

public record SalaInternacionRespuesta(Long id,
                                       Integer capacidad_maxima,
                                       List<PacienteRespuesta> pacientes) {
}
