package com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO;

import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoNuevo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ProfesionalDTO(@NotBlank String nombre,
                             @NotBlank String apellido,
                             @NotBlank @Pattern(regexp = "\\d{7,8}", message = "DNI inválido")
                             String dni,
                             /*@NotBlank @Pattern(
                                     regexp = "^[0-9+\\-\\s]{6,20}$",
                                     message = "El teléfono tiene un formato inválido"
                             )String telefono
                             ,
                              */
                             @NotBlank String matricula,// posible cambio debido a que puede ser null si no es profesional. VER
                             @NotNull ContactoNuevo contacto) {}
