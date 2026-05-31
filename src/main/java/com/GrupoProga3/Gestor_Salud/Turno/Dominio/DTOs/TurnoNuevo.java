package com.GrupoProga3.Gestor_Salud.Turno.Dominio.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

public record TurnoNuevo (@DateTimeFormat Date fecha,
                          Time hora,
                          @NotBlank String estado,
                          @NotNull Long id_paciente,
                          @NotNull Long id_tratamiento,
                          @NotNull Long id_sala,
                          @NotNull Long id_profesional){
}
