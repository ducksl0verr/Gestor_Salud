package com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioDTO(@NotBlank String nombre,
                         @NotBlank String apellido,
                         @NotBlank @Pattern(regexp = "\\d{7,8}", message = "DNI inválido") String dni,
                         @NotBlank @Pattern(
                                 regexp = "^[0-9+\\-\\s]{6,20}$",
                                 message = "El teléfono tiene un formato inválido"
                         )String telefono,
                         @NotBlank @Email String email){ // elimine el atributo de id_domicilioo FK, ver en usuarioMapperImpl.java si hay que cambiar, se encuentra en la carpeta target.
}


