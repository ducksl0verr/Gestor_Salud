package com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ContactoNuevo(

        @NotBlank(message = "El email no puede estar vacio")
        @Email
        String email,

        @NotBlank(message = "El teléfono no puede estar vacío")
        String telefono
) {
}
