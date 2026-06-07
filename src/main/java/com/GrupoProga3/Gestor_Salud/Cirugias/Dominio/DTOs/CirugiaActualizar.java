package com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.Enums.EstadoCirugia;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CirugiaActualizar(@NotNull LocalDate fecha,
                                @NotNull EstadoCirugia estado,
                                @NotNull Long idCirujano,
                                @NotNull Long idQuirofano) {
}
