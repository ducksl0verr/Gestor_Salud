package com.GrupoProga3.Gestor_Salud.features.Medicamentos.Dominio.DTOs;

import java.time.LocalDate;

public record MedicamentoRespuesta(Long id,
                                   String nombre,
                                   String principioActivo,
                                   String laboratorio,
                                   String descripcion,
                                   Integer stock,
                                   Double precio,
                                   LocalDate fechaVencimiento) {
}
