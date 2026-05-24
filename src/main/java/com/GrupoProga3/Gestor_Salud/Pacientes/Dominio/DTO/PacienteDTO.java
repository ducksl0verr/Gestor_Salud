package com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record PacienteDTO(@NotBlank String nombre,
                          @NotBlank String apellido,
                          @Temporal(TemporalType.DATE) Date fecha_nacimiento){}


