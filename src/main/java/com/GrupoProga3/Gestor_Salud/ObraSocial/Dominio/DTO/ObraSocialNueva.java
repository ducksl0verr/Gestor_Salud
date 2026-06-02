package com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ObraSocialNueva(@NotBlank(message = "El nombre no puede estar vacío")
                              @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
                              String nombre,
                              @NotBlank(message = "La cobertura no puede estar vacía")
                              String cobertura) {
}
