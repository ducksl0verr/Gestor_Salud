package com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TurnoNuevo(@NotNull LocalDateTime fechaHora,
                         @NotNull Long id_paciente,
                         Long id_tratamiento,
                         @NotNull Long id_consultorio,
                         @NotNull Long id_profesional) {
}
