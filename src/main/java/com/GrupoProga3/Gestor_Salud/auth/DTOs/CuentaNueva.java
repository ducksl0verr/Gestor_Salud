package com.GrupoProga3.Gestor_Salud.auth.DTOs;

import com.GrupoProga3.Gestor_Salud.auth.permisos.EntidadRole;
import com.GrupoProga3.Gestor_Salud.auth.permisos.ROLES;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record CuentaNueva(@NotBlank String nombre, @NotBlank String apellido,
                          String matricula,
                          String dni,
                          @NotNull
                          ContactoNuevo contacto,
                          @NotBlank String username, @NotBlank @Size(min = 8, max = 64) String password,
                          @NotNull ROLES role
                          ) {
}
