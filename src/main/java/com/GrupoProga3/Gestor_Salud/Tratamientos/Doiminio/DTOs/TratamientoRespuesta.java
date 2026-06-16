package com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.DTOs;

import java.math.BigDecimal;
import java.util.List;

public record TratamientoRespuesta(Long id,
                                   String nombre,
                                   String descripcion,
                                   BigDecimal precio) {
}
