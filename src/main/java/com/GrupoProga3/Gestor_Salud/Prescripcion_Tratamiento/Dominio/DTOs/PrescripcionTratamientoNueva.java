package com.GrupoProga3.Gestor_Salud.Prescripcion_Tratamiento.Dominio.DTOs;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PrescripcionTratamientoNueva(@NotNull Long id_paciente,
                                           @NotNull Long id_tratamiento,
                                           @NotNull Boolean activo,
                                           @NotNull
                                           LocalDate fecha_inicio,
                                           LocalDate fecha_final) {
}
