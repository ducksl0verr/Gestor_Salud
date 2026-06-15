package com.GrupoProga3.Gestor_Salud.features.Usuarios.Dominio.DTO;

import com.GrupoProga3.Gestor_Salud.auth.permisos.EntidadRole;
import com.GrupoProga3.Gestor_Salud.auth.permisos.ROLES;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioDTO(@NotBlank String nombre,
                         @NotBlank String apellido,
                         @NotBlank @Pattern(regexp = "\\d{7,8}", message = "DNI inválido")
                         String dni,
                         @NotNull
                         ContactoNuevo contacto,
                         @NotBlank
                         String username,
                         @NotBlank
                         String password,
                         @NotNull
                         ROLES role)
{}


