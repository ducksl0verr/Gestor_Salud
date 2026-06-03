package com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.DTOs;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CirugiaNueva(@NotNull
                           LocalDate fecha,
                           @NotNull
                           Long idPaciente,
                           @NotNull
                           Long idCirujano,
                           @NotNull
                           Long idQuirofano) {
}
