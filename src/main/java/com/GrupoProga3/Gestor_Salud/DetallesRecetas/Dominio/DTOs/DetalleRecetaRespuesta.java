package com.GrupoProga3.Gestor_Salud.DetallesRecetas.Dominio.DTOs;

public record DetalleRecetaRespuesta(Long id,
                                     String dosis,
                                     String frecuencia,
                                     Integer cantidad,
                                     String nombreMedicamento,
                                     Long idMedicamento) {
}
