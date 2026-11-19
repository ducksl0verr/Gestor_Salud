package com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TurnoFacturable(Long id_turno,
                              LocalDateTime fechaHora,
                              String tratamiento,
                              BigDecimal monto) {
}
