package com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactoNuevo(

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email no tiene un formato válido")
        @Size(max = 100, message = "El email no puede superar los 100 caracteres")
        String email,

        @NotBlank(message = "El teléfono no puede estar vacío")
        @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
        String telefono
) {
}
