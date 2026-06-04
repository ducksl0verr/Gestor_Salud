package com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.Enums.EstadoCirugia;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CirugiaNueva(@NotNull
                           LocalDate fecha,
                           EstadoCirugia estado,
                           @NotNull
                           Long idPaciente,
                           @NotNull
                           Long idCirujano,
                           @NotNull
                           Long idQuirofano) {
}
