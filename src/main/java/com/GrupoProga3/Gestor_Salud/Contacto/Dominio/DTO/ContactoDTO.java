package com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ContactoDTO(@NotBlank String nombre,
                          @NotBlank String apellido,
                          @NotBlank(message = "El teléfono no puede estar vacío")
                          @Pattern(
                                  regexp = "^[0-9+\\-\\s]{6,20}$",
                                  message = "El teléfono tiene un formato inválido"
                          )String telefono) {}


