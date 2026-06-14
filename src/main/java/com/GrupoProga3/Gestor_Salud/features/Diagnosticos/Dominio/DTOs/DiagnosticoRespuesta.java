package com.GrupoProga3.Gestor_Salud.features.Diagnosticos.Dominio.DTOs;

import java.time.LocalDate;

public record DiagnosticoRespuesta(Long id, String nombre, String descripcion,
                                   LocalDate fechaDiagnostico,
                                   Long idHistoriaClinica) {
}
