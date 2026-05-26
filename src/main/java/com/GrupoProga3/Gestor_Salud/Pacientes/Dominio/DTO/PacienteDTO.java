package com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public record PacienteDTO(@NotBlank String nombre,
                          @NotBlank String apellido,
                          @NotNull(message = "La fecha de nacimiento es obligatoria")
                          @Past(message = "La fecha debe ser anterior a hoy")
                          LocalDate fechaNacimiento) {}