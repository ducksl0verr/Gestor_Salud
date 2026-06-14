package com.GrupoProga3.Gestor_Salud.features.Prescripcion_Tratamiento.Dominio.DTOs;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PrescripcionTratamientoNueva(@NotNull Long id_paciente,
                                           @NotNull Long id_tratamiento,
                                           @NotNull Long id_profesional,
                                           @NotNull
                                           LocalDate fecha_inicio,
                                           LocalDate fecha_final) {
}
