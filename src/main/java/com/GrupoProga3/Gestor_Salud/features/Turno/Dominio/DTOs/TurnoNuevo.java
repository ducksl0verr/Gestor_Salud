package com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.DTOs;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public record TurnoNuevo (@Future LocalDateTime fechaHora,
                          @NotNull Long id_paciente,
                          @NotNull Long id_tratamiento,
                          @NotNull Long id_consultorio,
                          @NotNull Long id_profesional){
}
