package com.GrupoProga3.Gestor_Salud.auth.DTOs;

import com.GrupoProga3.Gestor_Salud.auth.permisos.EntidadRole;
import com.GrupoProga3.Gestor_Salud.auth.permisos.ROLES;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CuentaNueva(@NotBlank String nombre, @NotBlank String apellido,
                          String matricula,
                          String dni,
                          @NotNull
                          ContactoNuevo contacto,
                          @NotBlank String username, @NotBlank String password,
                          @NotNull ROLES role
                          ) {
}
