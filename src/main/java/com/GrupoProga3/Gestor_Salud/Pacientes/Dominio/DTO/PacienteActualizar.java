package com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialNueva;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PacienteActualizar(@NotBlank
                                 String nombre,
                                 @NotBlank
                                 String apellido,
                                 LocalDate fecha_nacimiento,
                                 @NotBlank
                                 String dni,
                                 @NotNull
                                 Long numeroAfiliado,
                                 @NotNull
                                 DomicilioNuevo domicilio,
                                 @NotNull
                                 ObraSocialNueva obraSocial) {
}
